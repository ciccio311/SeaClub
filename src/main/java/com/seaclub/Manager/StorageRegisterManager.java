package com.seaclub.Manager;

import com.seaclub.Model.Boat;
import com.seaclub.Model.StorageRegister;
import com.seaclub.server.DB;

import java.util.ArrayList;
import java.util.List;

public class StorageRegisterManager {
    private List<StorageRegister> registers;
    private StorageRegister lastStorageRegister;

    private static StorageRegisterManager instance = null;

    /*
     * The constructor is private so it is accessible only within the class.
     */
    private StorageRegisterManager(){
        registers = new ArrayList<StorageRegister>();
    }

    /**
     * The constructor is called only if the static instance is null, so only the first time
     * that the getInstance() method is invoked.
     * All the other times the same instance object is returned.
     * @return the instance object is returned.
     **/
    public static StorageRegisterManager getInstance() {
        if (instance == null)
            instance = new StorageRegisterManager();
        return instance;
    }

    public void setLastStorageRegister(StorageRegister lastStorageRegister) {
        this.lastStorageRegister = lastStorageRegister;
    }

    public List<StorageRegister> getRegisters() {
        return registers;
    }

    public void setRegisters(List<StorageRegister> registers) {
        this.registers = registers;
    }

    public void updateList(){
        DB.getInstance().getBoatStorageQuote();
    }

    public StorageRegister getLastStorageRegisterBoat(Boat boat){
        DB.getInstance().getLastBoatStorageQuote(boat);
        return lastStorageRegister;
    }

    public void addNewStorageRegister(StorageRegister storageRegister){
        try{
            DB.getInstance().addNewStorageRegister(storageRegister);
            updateList();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
