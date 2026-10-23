package com.vti.context;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountContext {
    private Map<String, Account> mapByEmail;
    private Map<String, Account> mapByUsername;
    private Map<Integer, Department> mapByDepID;
    private Map<Integer, Position> mapByPosID;
}
