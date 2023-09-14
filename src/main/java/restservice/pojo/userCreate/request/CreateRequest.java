package restservice.pojo.userCreate.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import restservice.pojo.userCreate.response.CreateResponse;
import restservice.pojo.userGet.response.GetResponse;

import java.util.Map;

public class CreateRequest  {

    private String age;
    private String gender;
    private String login;
    private String password;
    private String role;
    private String screenName;

    private CreateRequest(Builder builder) {
        this.age = builder.age;
        this.gender = builder.gender;
        this.login = builder.login;
        this.password = builder.password;
        this.role = builder.role;
        this.screenName = builder.screenName;
    }

    public CreateRequest setRole(String role) {
        this.role = role;
        return this;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getScreenName() {
        return screenName;
    }

    @Override
    public String toString() {
        return "CreateRequest{" +
                "age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", screenName='" + screenName + '\'' +
                '}';
    }

    public static class Builder {

        private String age;
        private String gender;
        private String login;
        private String password;
        private String role;
        private String screenName;

        public Builder buildAge(String age) {
            this.age = age;
            return this;
        }

        public Builder buildGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder buildLogin(String login) {
            this.login = login;
            return this;
        }

        public Builder buildPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder buildRole(String role) {
            this.role = role;
            return this;
        }

        public Builder buildScreenName(String screenName) {
            this.screenName = screenName;
            return this;
        }

        public Builder request(CreateRequest originalRequest) {
            this.age = originalRequest.age;
            this.gender = originalRequest.gender;
            this.login = originalRequest.login;
            this.password = originalRequest.password;
            this.screenName = originalRequest.screenName;
            this.role = originalRequest.role;
            return this;
        }

        public CreateRequest build() {
            return new CreateRequest(this);
        }
    }

    public Map<String, Object> toMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        Map<String, Object> map = objectMapper.convertValue(this, Map.class);
        map.put("role", role);
        return map;
    }

    public CreateResponse toCreateResponse() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        Map<String, Object> requestMap = objectMapper.convertValue(this, Map.class);
        return objectMapper.convertValue(requestMap, CreateResponse.class);
    }

    public GetResponse toGetResponse() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        Map<String, Object> requestMap = objectMapper.convertValue(this, Map.class);
        return objectMapper.convertValue(requestMap, GetResponse.class);
    }
}
