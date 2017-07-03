package com.controller;

import com.command.Command;
import com.command.CommandInt;
import com.command.PrintBalanceService;
import com.command.parser_command.DelegatedInputParser;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
public class RestCommandController {

    private DelegatedInputParser delegatedInputParser;

    @RequestMapping(value = "/commandTest", method = RequestMethod.POST)
    public List<PrintBalanceService> processCommand(@RequestBody Command command) {
        CommandInt commandInt = delegatedInputParser.defaultParseInput(command.getCommandName());
        return commandInt.executeDb(command.getAccountId());
    }

    @Resource(name = "delegatedInputParser")
    public void setDelegatedInputParser(DelegatedInputParser delegatedInputParser) {
        this.delegatedInputParser = delegatedInputParser;
    }
}