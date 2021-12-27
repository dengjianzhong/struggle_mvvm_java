package com.zhong.struggle_mvvm.logic.bean.print;

import java.util.List;

/**
 * @Author 李琪
 * @CreateTime 2021/12/14 12:07
 * @Description TODO
 */
public class DNBFoodMessageBean {

    private int FoodNameIsShow; //菜品名称是否显示  0:显示,1:不显示
    private String FoodNameValue; //菜品名称需要打印的名称
    private int FoodCountIsShow; //菜品数量是否显示  0:显示,1:不显示
    private String FoodCountValue; //菜品数量需要打印的名称
    private int FoodPriceIsShow; //菜品价格是否显示  0:显示,1:不显示
    private String FoodPriceValue; //菜品价格需要打印的名称
    private int FoodSubTotalIsShow; //菜品小计是否显示  0:显示,1:不显示
    private String FoodSubTotalValue; //菜品小计需要显示的名称
    private List<DNBFoodInfo> DNBFoodList; //菜品信息内容

    public int getFoodNameIsShow() {
        return FoodNameIsShow;
    }

    public String getFoodNameValue() {
        return FoodNameValue;
    }

    public int getFoodCountIsShow() {
        return FoodCountIsShow;
    }

    public String getFoodCountValue() {
        return FoodCountValue;
    }

    public int getFoodPriceIsShow() {
        return FoodPriceIsShow;
    }

    public String getFoodPriceValue() {
        return FoodPriceValue;
    }

    public int getFoodSubTotalIsShow() {
        return FoodSubTotalIsShow;
    }

    public String getFoodSubTotalValue() {
        return FoodSubTotalValue;
    }

    public List<DNBFoodInfo> getDNBFoodList() {
        return DNBFoodList;
    }

    public void setFoodNameIsShow(int foodNameIsShow) {
        FoodNameIsShow = foodNameIsShow;
    }

    public void setFoodNameValue(String foodNameValue) {
        FoodNameValue = foodNameValue;
    }

    public void setFoodCountIsShow(int foodCountIsShow) {
        FoodCountIsShow = foodCountIsShow;
    }

    public void setFoodCountValue(String foodCountValue) {
        FoodCountValue = foodCountValue;
    }

    public void setFoodPriceIsShow(int foodPriceIsShow) {
        FoodPriceIsShow = foodPriceIsShow;
    }

    public void setFoodPriceValue(String foodPriceValue) {
        FoodPriceValue = foodPriceValue;
    }

    public void setFoodSubTotalIsShow(int foodSubTotalIsShow) {
        FoodSubTotalIsShow = foodSubTotalIsShow;
    }

    public void setFoodSubTotalValue(String foodSubTotalValue) {
        FoodSubTotalValue = foodSubTotalValue;
    }

    public void setDNBFoodList(List<DNBFoodInfo> DNBFoodList) {
        this.DNBFoodList = DNBFoodList;
    }

    public static class DNBFoodInfo {
        private String FoodName; //菜品名称
        private int FoodCount; //菜品数量
        private String FoodPrice; //菜品价格
        private String SubTotal; //小计
        private List<DNBSetMealFoodItem> DNBSetMealFoodList; //套餐

        public String getFoodName() {
            return FoodName;
        }

        public int getFoodCount() {
            return FoodCount;
        }

        public String getFoodPrice() {
            return FoodPrice;
        }

        public String getSubTotal() {
            return SubTotal;
        }

        public List<DNBSetMealFoodItem> getDNBSetMealFoodList() {
            return DNBSetMealFoodList;
        }

        public void setFoodName(String foodName) {
            FoodName = foodName;
        }

        public void setFoodCount(int foodCount) {
            FoodCount = foodCount;
        }

        public void setFoodPrice(String foodPrice) {
            FoodPrice = foodPrice;
        }

        public void setSubTotal(String subTotal) {
            SubTotal = subTotal;
        }

        public void setDNBSetMealFoodList(List<DNBSetMealFoodItem> DNBSetMealFoodList) {
            this.DNBSetMealFoodList = DNBSetMealFoodList;
        }
    }

    public static class DNBSetMealFoodItem {
        private String SMFoodName; //套餐菜品名称
        private String SMFoodCount; //套餐菜品数量

        public String getSMFoodName() {
            return SMFoodName;
        }

        public String getSMFoodCount() {
            return SMFoodCount;
        }

        public void setSMFoodName(String SMFoodName) {
            this.SMFoodName = SMFoodName;
        }

        public void setSMFoodCount(String SMFoodCount) {
            this.SMFoodCount = SMFoodCount;
        }
    }

}
