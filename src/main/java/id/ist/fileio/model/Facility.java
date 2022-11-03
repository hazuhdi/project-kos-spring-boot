package id.ist.fileio.model;

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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Facility implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4687294306944554526L;
	

	@NotNull(message = "Id Facility Can be Null")
	private Long id;
	
	@NotNull(message = "Facility Name Cant be Null")
	@Size(min = 2, max = 15, message = "Facility Name should be between 2 to 15 characters")
	private String name;
	
	@NotNull(message = "Price is mandatory, or zero if its free")
	@PositiveOrZero
	private BigDecimal price;

}