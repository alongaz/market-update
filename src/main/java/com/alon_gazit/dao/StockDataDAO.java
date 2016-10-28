package com.alon_gazit.dao;

import com.alon_gazit.model.StockData;
import com.alon_gazit.model.Symbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by alon.g on 10/28/2016.
 */
@Component
public class StockDataDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final String SQL_SELECT_QUERY = "select * from STOCKS_DATA WHERE  STOCK_ID=?";

    private static final String SQL_CLEAR_COMMAND = "DELETE from STOCKS_DATA";

    private static final String SQL_UPDATE_COMMAND = "UPDATE STOCKS_DATA SET LAST_STOCK_PRICE=? WHERE STOCK_ID=? ";

    private static final String SQL_INSERT_COMMAND = "INSERT INTO STOCKS_DATA VALUES(?,?,?,?,?,?,?) ";


    public StockData getStockData(Symbol symbol){
        StockData answer = new StockData();
        Map<String, Object> row = jdbcTemplate.queryForMap(SQL_SELECT_QUERY,symbol.getId());
        answer.setLastPrice(Double.parseDouble((String)row.get("LAST_STOCK_PRICE")));
        answer.setEntryPrice(Double.parseDouble((String)row.get("ENTRY_PRICE")));
        answer.setExitPrice(Double.parseDouble((String)row.get("EXIT_PRICE")));
        answer.setRange(Double.parseDouble((String)row.get("RANGE")));
        answer.setPositionSize(Integer.parseInt((String)row.get("POSITION_SIZE")));
        answer.setSymbol(symbol);
        return answer;
    }

    public void clearTable(){
        jdbcTemplate.execute(SQL_CLEAR_COMMAND);
    }

    public void updateLastPrice(Symbol symbol, double lastPrice){
        jdbcTemplate.update(SQL_UPDATE_COMMAND,new Object[]{lastPrice,symbol.getId()});
    }


    public void insertStockDatas(List<StockData> stockDataList){
        int index = 0;
        for (StockData stockData: stockDataList) {
            jdbcTemplate.update(SQL_INSERT_COMMAND,
                    new Object[]{index++, stockData.getSymbol().getId(), stockData.getLastPrice(),
                            stockData.getEntryPrice(), stockData.getExitPrice(), stockData.getRange(),
                            stockData.getPositionSize()});
        }
    }
}
