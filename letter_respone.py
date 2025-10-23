import torch
from transformers import AutoModelForCausalLM, AutoTokenizer, pipeline
from fastapi import FastAPI, Request
from pydantic import BaseModel
import uvicorn

# -------------------------------
# Load Qwen model
# -------------------------------
QWEN_MODEL_NAME = "Qwen/Qwen1.5-1.8B-Chat"

print("⏳ Loading Qwen model...")
qwen_tokenizer = AutoTokenizer.from_pretrained(QWEN_MODEL_NAME, trust_remote_code=True)
qwen_model = AutoModelForCausalLM.from_pretrained(
    QWEN_MODEL_NAME,
    torch_dtype=torch.float16 if torch.cuda.is_available() else torch.float32,
    device_map="auto",
    trust_remote_code=True
)

# Tạo pipeline cho sinh văn bản
qwen_pipe = pipeline(
    "text-generation",
    model=qwen_model,
    tokenizer=qwen_tokenizer,
    device_map="auto"
)

print("✅ Qwen loaded successfully!")

# -------------------------------
# API setup
# -------------------------------
app = FastAPI(title="Qwen Chat API")

class ChatRequest(BaseModel):
    message: str

@app.post("/chat")
def generate_chat(req: ChatRequest):
    """Sinh phản hồi động viên"""
    prompt = f"""Bạn là một người bạn đồng cảm và nhẹ nhàng. 
Hãy phản hồi ngắn gọn (1-2 câu), chân thành và an ủi người đang buồn hoặc lo lắng.
Không cần lặp lại cảm xúc, chỉ cần thể hiện sự thấu hiểu và động viên.

Lời tâm sự: "{req.message}"

Phản hồi:"""
    
    response = qwen_pipe(
        prompt,
        max_new_tokens=128,
        do_sample=True,
        temperature=0.7,
        top_p=0.9,
        pad_token_id=qwen_tokenizer.eos_token_id
    )
    
    full_text = response[0]["generated_text"]
    reply = full_text.split("Phản hồi:")[-1].strip()
    return {"reply": reply}

# -------------------------------
# Test endpoint
# -------------------------------
@app.get("/")
def root():
    return {"message": "Qwen Chat API is running"}

# -------------------------------
# Run server
# -------------------------------
if __name__ == "__main__":
    uvicorn.run("app:app", host="0.0.0.0", port=8000)
