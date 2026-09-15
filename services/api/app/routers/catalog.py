from fastapi import APIRouter

router = APIRouter(prefix="/products", tags=["catalog"])


@router.get("")
def list_products() -> dict[str, list]:
    return {"items": []}

