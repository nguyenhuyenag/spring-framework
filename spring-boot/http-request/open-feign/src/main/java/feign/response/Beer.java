package feign.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Beer {

    private int id;
    private String uid;
    private String brand;
    private String name;
    private String style;
    private String hop;
    private String yeast;
    private String malts;
    private String ibu;
    private String alcohol;
    private String blg;

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", style='" + style + '\'' +
                ", hop='" + hop + '\'' +
                ", yeast='" + yeast + '\'' +
                ", malts='" + malts + '\'' +
                ", ibu='" + ibu + '\'' +
                ", alcohol='" + alcohol + '\'' +
                ", blg='" + blg + '\'' +
                '}';
    }
}
