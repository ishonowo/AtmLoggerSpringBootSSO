package com.infinity.app.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.infinity.app.model.TerminalObjects;
import com.infinity.app.model.Terminals;
import com.infinity.app.repo.TerminalRepo;
import com.infinity.app.repo.TerminalObjectRepo;

@Service
public class TerminalService {
	private final TerminalRepo terminalRepo;
	private final TerminalObjectRepo terminalObjectRepo;

	public TerminalService(TerminalRepo terminalRepo,TerminalObjectRepo terminalObjectRepo) {
			this.terminalRepo=terminalRepo;
			this.terminalObjectRepo=terminalObjectRepo;
		}

	public Terminals insertTerminal(Terminals terminal) {
		return terminalRepo.save(terminal);
	}

	/*public List<Terminals> getAllTerminals() {
		return terminalRepo.findAll();
	}*/
	
	public List<TerminalObjects> getAllTerminalObjects(){
		return terminalObjectRepo.findAllTerminalObjects();
	}

}
