package com.example.restapi.shop.dto;

import com.example.restapi.shop.entity.Shop;
import java.util.List;

public class ShopListResponse {
    private List<ShopItemDTO> shops;
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private boolean hasNext;

    // 建構子
    public ShopListResponse(List<ShopItemDTO> shops, int currentPage, int totalPages, long totalElements) {
        this.shops = shops;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.hasNext = currentPage < totalPages - 1;
    }

    // Getters & Setters
    public List<ShopItemDTO> getShops() {
        return shops;
    }

    public void setShops(List<ShopItemDTO> shops) {
        this.shops = shops;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }
}