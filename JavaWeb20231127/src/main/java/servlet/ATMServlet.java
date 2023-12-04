package servlet;

/**

綜合練習題目：模擬 ATM 機
假設您要為一家銀行開發一個簡單的 ATM 模擬系統。您需要實現一個 Servlet，該 Servlet 可以處理用戶的存款和提款操作。

功能要求：
查詢餘額：
路徑：/atm/balance
參數：用戶ID (userId)
返回：用戶的當前餘額

存款：
路徑：/atm/deposit
參數：用戶ID (userId), 存款金額 (amount)
返回：存款後的餘額

提款：
路徑：/atm/withdraw
參數：用戶ID (userId), 提款金額 (amount)
返回：提款後的餘額，如果餘額不足，返回錯誤信息

實現提示：
使用 Map<String, Double> 或其他數據結構來存儲用戶的銀行餘額。
需要考慮同步問題，因為多個請求可能同時操作同一用戶的賬戶。
 * */

public class ATMServlet {

}
