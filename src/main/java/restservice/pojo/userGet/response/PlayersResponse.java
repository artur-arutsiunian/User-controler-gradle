package restservice.pojo.userGet.response;

import java.util.List;
import java.util.Objects;

public class PlayersResponse {
    private List<PlayerResponse> players;

    public PlayersResponse() {
    }

    public PlayersResponse(List<PlayerResponse> players) {
        this.players = players;
    }

    public List<PlayerResponse> getPlayers() {
        return players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayersResponse that = (PlayersResponse) o;
        return Objects.equals(players, that.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players);
    }

    @Override
    public String toString() {
        return "PlayersResponse{" +
                "players=" + players +
                '}';
    }
}
