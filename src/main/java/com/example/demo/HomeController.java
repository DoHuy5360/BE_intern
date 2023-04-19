package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.account.Account;
import com.example.demo.entity.account.AccountRepository;
import com.example.demo.kit.query.DatabaseStructureQuery;
import com.example.demo.kit.query.DatabaseTableNameQuery;
import com.example.demo.kit.tray.DatabaseStructureTray;
import com.example.demo.kit.tray.DatabaseTableNameTray;

import lombok.Getter;
import lombok.Setter;

@Controller
public class HomeController {
    @Autowired
    DatabaseStructureQuery databaseStructureQuery;
    @Autowired
    DatabaseTableNameQuery databaseTableNameQuery;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("git", "de");
        return "home";
    }

    @GetMapping("/database")
    public String database(Model model) {
        List<tableObjectTray> totArray = new ArrayList<tableObjectTray>();
        List<DatabaseTableNameTray> dtnTray = databaseTableNameQuery.getAllTableName();
        for (DatabaseTableNameTray table : dtnTray) {
            String tableName = table.getTablename();
            List<DatabaseStructureTray> columns = databaseStructureQuery.getDataStructure(tableName);
            totArray.add(new tableObjectTray(tableName, columns));
        }
        model.addAttribute("amountTable", dtnTray.size());
        model.addAttribute("totArray", totArray);
        return "database";
    }
}

@Getter
@Setter
class tableObjectTray {
    public String name;
    public List<DatabaseStructureTray> columns;

    public tableObjectTray(String name, List<DatabaseStructureTray> columns) {
        this.name = name;
        this.columns = columns;
    }
}