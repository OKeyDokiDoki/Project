from fastapi import APIRouter
from pydantic import BaseModel

router = APIRouter(prefix="/recommendations", tags=["recommendations"])


class RecommendationRequest(BaseModel):
    user_id: str
    scene: str | None = None
    style: str | None = None


@router.post("")
def create_recommendations(payload: RecommendationRequest) -> dict:
    return {
        "user_id": payload.user_id,
        "items": [],
        "message": "推荐引擎尚未接入",
    }

