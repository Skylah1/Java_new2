package ui;

import java.util.Vector;
import manage.ExpenseLimit;

import Category.CategoryInfo;
import database.Calculator;
import database.DB;

public class Main {
   public static void main(String[] args) {
      DB db=new DB();
      CategoryInfo CInfo = new CategoryInfo();
      Calculator C = new Calculator(db.getDatas(), db.getCurBalance());
      String M = C.Money("0");
      String M1 = C.Money("1");
      String M2 = C.Money("2");
      Vector<Vector<String>> A = C.CalcPerCategorysum();
      Vector<Vector<String>> B = C.CalcPerCategoryKRW();
      Vector<Vector<String>> D = C.CalcPerCategoryUSD();
      
   }
}