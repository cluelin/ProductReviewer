package com.sideproject.productreviewer

import android.os.Parcel
import android.os.Parcelable

class ProductInfo(var productName: String?, var productDescription: String?, var productRate: Int) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(productName)
        parcel.writeString(productDescription)
        parcel.writeInt(productRate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductInfo> {
        override fun createFromParcel(parcel: Parcel): ProductInfo {
            return ProductInfo(parcel)
        }

        override fun newArray(size: Int): Array<ProductInfo?> {
            return arrayOfNulls(size)
        }
    }


}