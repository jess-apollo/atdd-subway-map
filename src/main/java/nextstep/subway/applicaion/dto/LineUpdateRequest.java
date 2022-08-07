package nextstep.subway.applicaion.dto;

import lombok.Builder;
import nextstep.subway.domain.Line;

@Builder
public class LineUpdateRequest {

    private String name;

    private String color;

    public LineUpdateRequest(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Line toLine() {
        return new Line(null, this.name, this.color, null, null);
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
