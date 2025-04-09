package com.gbg.usersevice.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class IndexData {
    @JsonProperty("data")
    private DataContent data;
}

@Data
class DataContent {
    @JsonProperty("indexCloseOnlineRecords")
    private List<IndexCloseOnlineRecord> indexCloseOnlineRecords;
    
    @JsonProperty("indexTurnoverRecords")
    private List<IndexTurnoverRecord> indexTurnoverRecords;
}

@Data
class IndexCloseOnlineRecord {
    @JsonProperty("_id")
    private String id;
    
    @JsonProperty("EOD_INDEX_NAME")
    private String eodIndexName;
    
    @JsonProperty("EOD_OPEN_INDEX_VAL")
    private double eodOpenIndexVal;
    
    @JsonProperty("EOD_HIGH_INDEX_VAL")
    private double eodHighIndexVal;
    
    @JsonProperty("EOD_CLOSE_INDEX_VAL")
    private double eodCloseIndexVal;
    
    @JsonProperty("EOD_LOW_INDEX_VAL")
    private double eodLowIndexVal;
    
    @JsonProperty("EOD_TIMESTAMP")
    private String eodTimestamp;
    
    @JsonProperty("TIMESTAMP")
    private String timestamp;
}

@Data
class IndexTurnoverRecord {
    @JsonProperty("_id")
    private String id;
    
    @JsonProperty("HIT_INDEX_NAME_UPPER")
    private String hitIndexNameUpper;
    
    @JsonProperty("HIT_TRADED_QTY")
    private long hitTradedQty;
    
    @JsonProperty("HIT_TURN_OVER")
    private double hitTurnOver;
    
    @JsonProperty("HIT_TIMESTAMP")
    private String hitTimestamp;
    
    @JsonProperty("TIMESTAMP")
    private String timestamp;
}

