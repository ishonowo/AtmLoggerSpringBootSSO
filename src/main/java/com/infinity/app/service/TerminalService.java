package com.infinity.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.infinity.app.dto.TerminalWNames;
import com.infinity.app.dto.VendorContactObject;
import com.infinity.app.model.Terminals;
import com.infinity.app.repo.TerminalRepo;
//import com.infinity.app.repo.TerminalObjectRepo;

@Service
public class TerminalService {
	private final TerminalRepo terminalRepo;
	//private final TerminalObjectRepo terminalObjectRepo;

	public TerminalService(TerminalRepo terminalRepo) {
			this.terminalRepo=terminalRepo;
		}

	public Terminals insertTerminal(Terminals terminal) {
		return terminalRepo.save(terminal);
	}

	public List<TerminalWNames> findAllTerminalsWNames() {
        return terminalRepo.findAllTerminalsWNames()
            .stream()
            .map(projection -> new TerminalWNames(
                projection.vendorId(),
                projection.vendorName(),
                projection.id(),
                projection.terminalId(),
                projection.atmName(),
                projection.offsite()
                ))
            .collect(Collectors.toList());
    }
	
	/*public List<TerminalObjects> getAllTerminalObjects(){
		return terminalObjectRepo.findAllTerminalObjects();
	}*/

}
