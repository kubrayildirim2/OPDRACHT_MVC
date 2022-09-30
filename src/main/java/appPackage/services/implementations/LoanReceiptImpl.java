package appPackage.services.implementations;

import appPackage.data.Computer;
import appPackage.data.LoanReceipt;
import appPackage.data.Student;
import appPackage.repositories.LoanReceiptRepository;
import appPackage.services.interfaces.LoanReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanReceiptImpl implements LoanReceiptService {

    private LoanReceiptRepository loanReceiptRepository;


    public LoanReceiptImpl() {
    }

    @Autowired
    public LoanReceiptImpl(LoanReceiptRepository loanReceiptRepository) {
        this.loanReceiptRepository = loanReceiptRepository;
    }

    @Override
    public List<LoanReceipt> getAllReceipts() {
        return loanReceiptRepository.findAll();
    }

    @Override
    public List<LoanReceipt> getAllOpenReceipts() {

        return loanReceiptRepository.findByEndDateIsNull();
    }

    //TODO: Make this method return every receipt that has no end Date, that has been open for more than 9 months
    //TODO: Example1: A computer has been lend out the 28/10/2020 and not yet returned. It's been over 9 months. This needs to be on the list
    //TODO: Example2: A computer has been loaned on 11/07/2022 and not yet returned. It's only been 2 months, this should not be on the list
    @Override
    public List<LoanReceipt> getAllOpenReceiptsLongerThan9Months() {
        return null;
    }

    //TODO: make a Method that lends a computer to a student. Make sure that the student is not blacklisted
    //TODO: , that the student doesn't already own a computer and that the PC is not already in use.
    //TODO: If it was succesful, the method returns true. When lending, startDate is today
    @Override
    public boolean loanComputerToStudent(Computer computer, Student student) {
        
        return false;
    }


    @Override
    public boolean isPcInUse(Computer computer) {
        LoanReceipt foundReceipt = loanReceiptRepository.findByComputerAndEndDateIsNull(computer);
        if (foundReceipt!=null){
            return true;
        }
        return false;
    }

    @Override
    public Computer checkIfStudentCurrentlyOwnsPC(Student student) {
        LoanReceipt foundReceipt = loanReceiptRepository.findByStudentAndEndDateIsNull(student);
        if (foundReceipt != null){
            return foundReceipt.getLoanedComputer();
        }
        return null;
    }
}
