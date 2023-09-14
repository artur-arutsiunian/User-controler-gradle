package restservice.pojo.userPatch.request;

public class PatchRequest {

    private Integer age;

    private PatchRequest(Builder builder) {
        this.age = builder.age;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "PatchRequest{" +
                "age=" + age +
                '}';
    }

    public static class Builder {

        private Integer age;

        public Builder buildPlayerAge(Integer age) {
            this.age = age;
            return this;
        }

        public PatchRequest build() {
            return new PatchRequest(this);
        }
    }
}
