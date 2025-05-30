package com.kritica.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//This class will read client data (request)
// and send to server which will convert this DTO to an entity
// and send to the repository to fetch data from a database
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private String imageAlt;
    private String create_by;
    private String update_by;
}
