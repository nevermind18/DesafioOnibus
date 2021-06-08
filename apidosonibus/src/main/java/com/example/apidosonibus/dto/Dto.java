package com.example.apidosonibus.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Dto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5888311220297695790L;
    /**
     * @return the id
     */
    Map<String, Object> details = new LinkedHashMap<>();

    @JsonAnySetter
    void setDetail(String key, Object value) {
        details.put(key, value);
    }

    /**
     * @return the details
     */
    public Map<String, Object> getDetails() {
        return details;
    }

    


}
