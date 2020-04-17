package specification;

import filter.AllureRestAssuredFilter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SpecCreator {

    private Map<String, String> param;
    private Map<String, Map<String, String>> params;
    private Map<String, List<String>> list;
    private Map<String, String> headers;
    private Map<String, String> queryParam;

    private SpecCreator(Builder builder){
        this.param = builder.buildParam;
        this.params = builder.buildParams;
        this.list = builder.buildList;
        this.headers = builder.buildHeaders;
        this.queryParam = builder.builderQueryParam;
    }

    private Map<String, String> getParam() {
        return param;
    }

    private Map<String, Map<String, String>> getParams() {
        return params;
    }

    private Map<String, List<String>> getList() {
        return  list;
    }

    private Map<String, String> getHeaders() {
        return headers;
    }

    private Map<String, String> getQueryParam() {
        return queryParam;
    }

    public static class Builder {
        private Map<String, String> buildParam = new HashMap<>();
        private Map<String, Map<String, String>> buildParams = new HashMap<>();
        private Map<String, List<String>> buildList = new HashMap<>();
        private Map<String, String> buildHeaders = new HashMap<>();
        private Map<String, String> builderQueryParam = new HashMap<>();

        public Builder() {
        }

        public Builder addParam(String key, String value) {
            buildParam.put(key, value);
            return this;
        }

        public Builder addParams(String key, Map<String, String> value) {
            buildParams.put(key, value);
            return this;
        }

        public Builder addList(String key, List<String> value) {
            buildList.put(key, value);
            return this;
        }

        public Builder addHeader(String key, String value) {
            buildHeaders.put(key, value);
            return this;
        }

        public Builder addQueryParam(String key, String value) {
            builderQueryParam.put(key, value);
            return this;
        }

        public SpecCreator build(){
            return new SpecCreator(this);
        }
    }

    public RequestSpecification createSpec() {
        RequestSpecBuilder reqSpec = new RequestSpecBuilder();
        reqSpec.addParams(getParam());
        reqSpec.addParams(getParams());
        reqSpec.addParams(getList());
        reqSpec.addHeaders(getHeaders());
        reqSpec.addQueryParams(getQueryParam());
        return reqSpec.build().filter(new AllureRestAssuredFilter()
                .setRequestTemplate("http-request.ftl")
                .setResponseTemplate("http-response.ftl"));
    }
}