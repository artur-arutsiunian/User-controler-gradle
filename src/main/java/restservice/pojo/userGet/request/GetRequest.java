package restservice.pojo.userGet.request;

public class GetRequest {

    private Integer playerId;

    private GetRequest(Builder builder) {
        this.playerId = builder.playerId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    @Override
    public String toString() {
        return "GetRequest{" +
                "playerId=" + playerId +
                '}';
    }

    public static class Builder {

        private Integer playerId;

        public Builder buildPlayerId(Integer playerId) {
            this.playerId = playerId;
            return this;
        }

        public GetRequest build() {
            return new GetRequest(this);
        }
    }
}
