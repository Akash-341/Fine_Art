package com.ort.fineart.Model.Request_Model.UserAddress;

public interface UpdateDeleteAddress_Interface {


    void deleteAddress(int AddressId);

    void updateAddress(int addressId,String AddressLine1,String AddressLine2 ,String Country ,String State ,String City, int Pincode);


}
