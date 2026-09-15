from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from app.routers import catalog, recommendations, try_on

app = FastAPI(
    title="AI Smart Mirror API",
    version="0.1.0",
    description="AI 试衣镜业务 API 脚手架",
)

app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:5173"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

app.include_router(catalog.router, prefix="/api/v1")
app.include_router(recommendations.router, prefix="/api/v1")
app.include_router(try_on.router, prefix="/api/v1")


@app.get("/health", tags=["system"])
def health() -> dict[str, str]:
    return {"status": "ok", "service": "smart-mirror-api"}

