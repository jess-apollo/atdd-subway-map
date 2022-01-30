package nextstep.subway.application.dto.response;

import nextstep.subway.domain.Line;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class LineResponse {
	private Long id;
	private String name;
	private String color;
	private List<StationResponse> stations;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;

	private LineResponse() {
	}

	private LineResponse(Long id, String name, String color, List<StationResponse> stations, LocalDateTime createdDate, LocalDateTime modifiedDate) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.stations = stations;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public static LineResponse from(Line line) {
		return new LineResponse(
				line.getId(),
				line.getName(),
				line.getColor(),
				StationResponse.ofSections(line.getSections()),
				line.getCreatedDate(),
				line.getModifiedDate());
	}

	public static List<LineResponse> fromList(List<Line> lines) {
		return lines.stream().map(LineResponse::from).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public List<StationResponse> getStations() {
		return stations;
	}
}