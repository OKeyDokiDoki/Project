export type BodyShape = "pear" | "apple" | "h" | "a" | "y";
export type SkinTone = "cool" | "warm" | "neutral";
export type FitPreference = "slim" | "regular" | "relaxed";

export interface BodyProfile {
  heightCm: number;
  shoulderCm?: number;
  chestCm?: number;
  waistCm?: number;
  hipCm?: number;
  legLengthCm?: number;
  bodyShape?: BodyShape;
  skinTone?: SkinTone;
}

export interface Product {
  id: string;
  name: string;
  category: "top" | "bottom" | "dress" | "outerwear" | "shoes" | "accessory";
  price: number;
  colors: string[];
  sizes: string[];
  stock: number;
  assetUrl?: string;
}

export interface TryOnSession {
  id: string;
  userId: string;
  productIds: string[];
  status: "pending" | "processing" | "ready" | "failed";
  resultUrl?: string;
  createdAt: string;
}

