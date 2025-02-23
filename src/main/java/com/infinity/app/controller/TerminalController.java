package com.infinity.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infinity.app.dto.TerminalWNames;
import com.infinity.app.model.Terminals;
import com.infinity.app.service.TerminalService;


@RestController
@RequestMapping("/api/terminals")
public class TerminalController {

    private final TerminalService terminalService;
    
    public TerminalController(TerminalService terminalService) {
    	this.terminalService=terminalService;
    }
    // Fetch all terminals
/*    @GetMapping
    public ResponseEntity<List<Terminals>> getAllTerminals() {
        List<Terminals> terminals = terminalService.getAllTerminals();
        System.out.println(terminals);
        return ResponseEntity.ok(terminals);
    }*/

    // Fetch all terminals with vendor names
    @GetMapping
    public ResponseEntity<List<TerminalWNames>> getAllTerminalObjs() {
        List<TerminalWNames> newTerminals = terminalService.findAllTerminalsWNames();
        System.out.println(newTerminals);
        return ResponseEntity.ok(newTerminals);
    }

    // Insert a new terminal
    @PostMapping
    public ResponseEntity<Terminals> insertTerminal(@RequestBody Terminals terminal) {
    	Terminals savedTerminal = terminalService.insertTerminal(terminal);
        return ResponseEntity.ok(savedTerminal);
    }
}
