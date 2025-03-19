package com.infinity.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.infinity.app.dto.TerminalWithNames;
import com.infinity.app.model.Terminal;
import com.infinity.app.repo.TerminalRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TerminalService {
	private final TerminalRepo terminalRepo;
	//private final TerminalObjectRepo terminalObjectRepo;

	public TerminalService(TerminalRepo terminalRepo) {
			this.terminalRepo=terminalRepo;
		}

	public Terminal insertTerminal(Terminal terminal) {
		return terminalRepo.save(terminal);
	}

	public List<TerminalWithNames> findAllTerminalsWithNames() {
        return terminalRepo.findAllTerminalsWithNames()
            .stream()
            .map(projection -> new TerminalWithNames(
                projection.getVendorId(),
                projection.getVendorName(),
                projection.getId(),
                projection.getTerminalId(),
                projection.getAtmName(),
                projection.getOffsite()
                ))
            .collect(Collectors.toList());
    }

	public Terminal updateTerminal(TerminalWithNames updatedTerminal) {
		Terminal terminal = terminalRepo.findById(updatedTerminal.getId())
	            .orElseThrow(() -> new EntityNotFoundException("Terminal not found with id: " + updatedTerminal.getId()));
	        
		terminal.setTerminalId(updatedTerminal.getTerminalId());
        terminal.setAtmName(updatedTerminal.getAtmName());
        terminal.setVendorId(updatedTerminal.getVendorId());
        terminal.setOffsite(updatedTerminal.getOffsite());
        
		return terminalRepo.save(terminal);
	}
	
	/*public List<TerminalObjects> getAllTerminalObjects(){
		return terminalObjectRepo.findAllTerminalObjects();
	}*/

}
