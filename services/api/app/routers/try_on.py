from uuid import uuid4

from fastapi import APIRouter, status
from pydantic import BaseModel

router = APIRouter(prefix="/try-on", tags=["try-on"])


class TryOnRequest(BaseModel):
    user_id: str
    product_ids: list[str]
    source_asset_url: str | None = None


@router.post("/sessions", status_code=status.HTTP_202_ACCEPTED)
def create_session(payload: TryOnRequest) -> dict:
    return {
        "id": str(uuid4()),
        "user_id": payload.user_id,
        "product_ids": payload.product_ids,
        "status": "pending",
    }

