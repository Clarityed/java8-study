package java8.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 原神游戏类
 *
 * @author: clarity
 * @date: 2022年10月23日 10:25
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YuanShen {

    private List<Role> roleList;

}
