package restservice.pojo.userDelete;

public class DeleteRequest {

    private Integer playerId;

    private DeleteRequest(Builder builder) {
        this.playerId = builder.playerId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    @Override
    public String toString() {
        return "DeleteRequest{" +
                "playerId=" + playerId +
                '}';
    }

    public static class Builder {

        private Integer playerId;

        public Builder buildPlayerId(Integer playerId) {
            this.playerId = playerId;
            return this;
        }

        public DeleteRequest build() {
            return new DeleteRequest(this);
        }
    }
}