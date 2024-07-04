from fastapi import FastAPI, File, UploadFile
from fastapi.responses import JSONResponse
from fastapi import FastAPI, HTTPException
from fastapi.responses import StreamingResponse
from ultralytics import YOLO
import numpy as np
import cv2
from PIL import Image,ImageDraw
import io
from pydantic import BaseModel
from fastapi.middleware.cors import CORSMiddleware
import os
import json
import base64

app = FastAPI()

origins = [
    "http://localhost",
    "http://localhost:3000",
    "http://localhost:8081",
    "http://127.0.0.1:8000",
]

# CORS 설정
app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["GET", "POST", "PUT", "DELETE"],  # 필요한 HTTP 메소드 설정
    allow_headers=["*"],  # 모든 헤더 허용
)

# YOLO 모델 로드
# 칼,총,가위,랜치,펜치 탐지
model = YOLO(r'C:\Users\User\yoloray\msa-fastapi\models\v1.0.1_240613.pt') # 모델 경로 맞추어서 폴더 경로 이동해야함.

# 칼,총 탐지
model2 = YOLO(r'C:\Users\User\yoloray\msa-fastapi\models\v1.0.0_240610.pt') # 모델 경로 맞추어서 폴더 경로 이동해야함.
testnum = 0


# "/" 로 접속했을시
@app.get('/')
def home() :
    return "YOLO RAY"



# 그래서 clas 생성해줌.
# class model(BaseModel):
#     # 데이터 타입 정의
#     chmodel : str

@app.get("/modelchange/{modelName}")
async def modelChange(modelName : str) :
    global model, testnum  # 전역 변수 사용 선언

    if modelName == 'model1' :
        # 칼,총,가위,랜치,펜치 탐지
        model = YOLO(r'C:\Users\User\yoloray\msa-fastapi\models\v1.0.1_240613.pt') # 모델 경로 맞추어서 폴더 경로 이동해야함.
        testnum = 1
    else :  
        print(modelName)  
        # 칼,총 탐지
        model = YOLO(r'C:\Users\User\yoloray\msa-fastapi\models\v1.0.0_240610.pt') # 모델 경로 맞추어서 폴더 경로 이동해야함.
        testnum = 2
    print(testnum) 

    return "모델로 변경되었습니다."




# 그래서 class 생성해줌.
class SendImg(BaseModel):
    # 데이터 타입 정의
    imgname : str


#  yolo모델 동작시키는 api
@app.post("/predict")
async def predict(imgobj : SendImg):  # 이미지 파일 이름 가져옴.
     # imageName = (imgobj.imgname.split(sep='.')[0])+".png"
    global model, testnum  # 전역 변수 사용 선언

    imageName = imgobj.imgname  # JS로 짤라옴.
    print(imageName)
    print(testnum)

    try:
        # 이미지 저장시킬 경로 설정
        base_path = r'C:\Users\User\git\repository9\msa-spring\frontend\src\assets\img\yolo_img'  # react 폴더 경로
       # base_path = r'C:\Users\dkfld\Desktop\yoloray\frontend\public\detectImg'  # react 폴더 경로          
        image_path = os.path.join(base_path, imageName)
        # contents = await file.read() # 파일 읽어오기

        # PIL 사용
       # image = Image.open(imageName)
        #image = Image.open(io.BytesIO(imageName)).convert("RGB") # RGB 형식 변환
        #print(image)
        path1 = (r'C:\Users\User\git\repository9\msa-spring\frontend\src\assets\img\yolo_img') # react 폴더 경로
        fullPath = os.path.join(path1, imageName)
        print(fullPath)
        image_path2 = fullPath
        
        if not os.path.exists(image_path2):
            raise HTTPException(status_code=404, detail="Image not found")
    
        image = Image.open(image_path2).convert("RGB")  # 이미지 불러오기 및 RGB 형식 변환
        
        # numpy 형태로 변환
        image_np = np.array(image)
        
        # detection 진행
        results = model(image_np)

        detected_classes = set()
    
        # 탐지 결과 처리 및 이미지에 박스 그리기
        draw = ImageDraw.Draw(image)
        predictions = []
        
        for result in results:
            boxes = result.boxes.xyxy.tolist()  # xyxy 형식의 bounding box 좌표
            confs = result.boxes.conf.tolist()  # confidence score
            clss = result.boxes.cls.tolist()    # class index
            names = result.names
            
            for box, conf, cls in zip(boxes, confs, clss):
                x1, y1, x2, y2 = map(int, box)
                class_name = names[int(cls)]
                detected_classes.add(class_name)
                predictions.append({
                    "image_path" : image_path,
                    "box": [x1, y1, x2, y2],
                    "confidence": conf, # 정확도
                    "class": int(cls), #클래스 값
                    "name": class_name # 탐지된 객체 이름
                })
                # 이미지에 박스 그리기
                draw.rectangle([x1, y1, x2, y2], outline="red", width=4)
                draw.text((x1, y1), f"{class_name}: {conf:.2f}", fill="red")

        
        # 탐지된 클래스 이름을 리스트로 반환
        detected_classes = list(detected_classes)


        # react폴더 경로로 탐지 이미지 저장됨.
        output_path = os.path.join(base_path, "processed_" + imageName)
        image.save(output_path)
        print("save완료")
        
        # 이미지 바이너리로 변환
        img_byte_arr = io.BytesIO()
        image.save(img_byte_arr, format='PNG')
        img_byte_arr = img_byte_arr.getvalue()

        
         # 이미지 스트리밍 응답
       # return StreamingResponse(io.BytesIO(img_byte_arr), media_type="image/png")

        return JSONResponse(content={"predictions": predictions})

    except HTTPException as http_ex:
        # HTTPException을 다시 발생시킴
        raise http_ex

    except Exception as e:
        # 기타 모든 예외 처리
        print(f"An error occurred: {e}")
        raise HTTPException(status_code=500, detail="Internal Server Error")




if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8000, debug=True)  # 메인