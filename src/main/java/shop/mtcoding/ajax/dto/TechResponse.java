package shop.mtcoding.ajax.dto;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.ajax.model.Category;
import shop.mtcoding.ajax.model.Tech;

public class TechResponse {
    @Getter
    @Setter
    public static class MainDTO {
        private List<CategoryDTO> categories;
        private List<TechDTO> techs;

        public MainDTO(List<Category> categories, List<Tech> techs) {
            this.categories = categories.stream().map(CategoryDTO::new).collect(Collectors.toList());
            this.techs = techs.stream().map(TechDTO::new).collect(Collectors.toList());
        }

        @Getter
        @Setter
        public class CategoryDTO {
            private Integer id;
            private String name;

            public CategoryDTO(Category category) {
                this.id = category.getId();
                this.name = category.getName();
            }
        }

        @Getter
        @Setter
        public class TechDTO {
            private Integer id;
            private String name;

            public TechDTO(Tech tech) {
                this.id = tech.getId();
                this.name = tech.getName();
            }
        }
    }
}