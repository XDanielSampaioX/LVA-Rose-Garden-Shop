const fallbackImages = [
  "/catalog/reference/rosa-pink.jpg",
  "/catalog/reference/rosa-red.jpg",
  "/catalog/reference/rosa-white.jpg",
  "/catalog/reference/rosa-multi.jpg",
  "/catalog/reference/rosa-purple.jpg",
  "/catalog/reference/rosa-seedling.jpg",
  "/catalog/reference/fertilizer.jpg",
  "/catalog/reference/vasos.jpg",
];

export function getCatalogImageSrc(index: number) {
  return fallbackImages[index % fallbackImages.length];
}
