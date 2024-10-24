from fastapi import APIRouter
from services.translation import perform_translation
from models.translation import TranslationRequest

router = APIRouter()

@router.post("/translate")
async def translate(request: TranslationRequest):
    result = perform_translation(request.text, request.lang)
    return {"result":result}