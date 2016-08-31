package cn.tju.scs.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * Created by lichen.ll on 2016/8/30.
 */

public @Data class UserDO {
    // 用户ID
    private Long    userId;
    // 用户名
    private String  userName;
    // 密码
    private String  password;
    // 主管ID
    private Long    bossId;
    // 联系电话
    private String  phone;
    // 结婚的次数，<=0代表未婚，大于0代表已婚
    private Integer marryTimes;
    // 性别,0代表男，1代表女
    private Integer sex;
    // 子女的数量
    private Integer childNum;
    // 年龄
    private Integer age;
    // 创建日期
    private Date    gmtCreate;
    // 修改日期
    private Date    gmtModified;
    // 用户权限，0代表部门主管，1代表组长，2代表普通员工
    private Integer userPower;
}
