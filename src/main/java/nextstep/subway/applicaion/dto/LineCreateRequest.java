package nextstep.subway.applicaion.dto;

import lombok.Builder;
import nextstep.subway.domain.Line;

@Builder
public class LineCreateRequest {

    private String name;

    private String color;

    private Long upStationId;

    private Long downStationId;

    private Long distance;

    public LineCreateRequest(String name, String color, Long upStationId, Long downStationId, Long distance) {
        this.name = name;
        this.color = color;
        this.upStationId = upStationId;
        this.downStationId = downStationId;
        this.distance = distance;
    }

    public Line toLine(LineCreateRequest lineCreateRequest) {
        return new Line(null, this.name, this.color, this.distance);
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public Long getUpStationId() {
        return upStationId;
    }

    public Long getDownStationId() {
        return downStationId;
    }

    public Long getDistance() {
        return distance;
    }
}
