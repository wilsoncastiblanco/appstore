package com.grability.appstore.utils;

import android.text.format.DateUtils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class StringUtil {

  /**
   * Return a String with the first capital letter
   *
   * @param text String to modify
   * @return String modify
   */
  public static String upperCaseFirstLetter(String text) {
    try {
      if (text.length() > 0) {
        text = String.valueOf(text.charAt(0)).toUpperCase() + text.subSequence(1, text.length());
      }
    } catch (Exception e) {}
    return text;
  }

  /**
   * Return a String with the difference date between now and the time sent
   *
   * @param time String with the time to compare(unixtime)
   * @return String with first capital letter and the difference
   */
  public static String getDateDifferenceString(String time) {
    try {
      long unixtime = Long.parseLong(time) * 1000l;
      int flags = DateUtils.FORMAT_NUMERIC_DATE;
      time = (String) DateUtils.getRelativeTimeSpanString(unixtime, System.currentTimeMillis(), 0, flags);
    } catch (Exception e) {
    }
    return upperCaseFirstLetter(time);
  }
  
  public static String getInCurrencyFormat(String value, String currencySymbol){
    NumberFormat df = NumberFormat.getCurrencyInstance();
    DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    dfs.setCurrencySymbol(currencySymbol);
    dfs.setGroupingSeparator('.');
    dfs.setMonetaryDecimalSeparator('.');
    df.setMaximumFractionDigits(0);
    ((DecimalFormat) df).setDecimalFormatSymbols(dfs);
    return df.format(Double.parseDouble(value));
  }


  public static String getFormatDecimal(double decimalValue) {
    NumberFormat nf = new DecimalFormat("##.##");
    return nf.format(decimalValue);
  }


}
