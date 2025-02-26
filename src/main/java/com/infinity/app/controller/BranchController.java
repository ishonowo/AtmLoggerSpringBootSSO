package com.infinity.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infinity.app.dto.BranchWithName;
import com.infinity.app.dto.TerminalWithNames;
import com.infinity.app.model.BranchInfo;
import com.infinity.app.model.Terminals;
import com.infinity.app.service.BranchService;

@RestController
@RequestMapping("api/branches")
public class BranchController {
	
	private final BranchService branchService;
	
	public BranchController(BranchService branchService) {
		this.branchService=branchService;
	}
	
	@GetMapping
	public ResponseEntity<List<BranchWithName>>
	findAllBranchObject(){
		List<BranchWithName> branches = branchService.findAllBranchesWithNames();
		System.out.println(branches);
		return ResponseEntity.ok(branches);
	}
	
	@GetMapping("/sol/validate/{sol}")
	public ResponseEntity<Boolean>
	findSol(@PathVariable String sol){
		Boolean solFound=branchService.findSol(sol);
		System.out.println("Sol was: "+solFound);
		return ResponseEntity.ok(solFound);
	}
	
	@PostMapping
    public ResponseEntity<BranchInfo> insertBranch(@RequestBody BranchInfo branch) {
    	BranchInfo savedBranch = branchService.insertBranch(branch);
        return ResponseEntity.ok(savedBranch);
    }
	
	@PutMapping
    public ResponseEntity<BranchInfo> updateVendor(@RequestBody BranchWithName updatedBranch) {
        return ResponseEntity.ok((BranchInfo)branchService.updateBranch(updatedBranch));
    }

}
