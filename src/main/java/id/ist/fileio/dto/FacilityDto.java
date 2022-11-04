package id.ist.fileio.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacilityDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6429035139748703801L;
	
	@NotNull(message = "Facility Name Cant be Null DTO")
	@Size(min = 2, max = 15, message = "Facility Name should be between 2 to 15 characters")
	private String name;
	
	@NotNull(message = "Price is mandatory, or zero if its free DTO")
	@PositiveOrZero
	private BigDecimal price;

}
