package com.developersbreach.cutepuppies.data

import android.content.Context
import com.developersbreach.cutepuppies.R
import com.developersbreach.cutepuppies.model.Cats
import com.developersbreach.cutepuppies.ui.theme.ChuchuEyeColor
import com.developersbreach.cutepuppies.ui.theme.ChuchuHairColor
import com.developersbreach.cutepuppies.ui.theme.CocoEyeColor
import com.developersbreach.cutepuppies.ui.theme.CocoHairColor
import com.developersbreach.cutepuppies.ui.theme.DdEyeColor
import com.developersbreach.cutepuppies.ui.theme.DdHairColor
import com.developersbreach.cutepuppies.ui.theme.LalaEyeColor
import com.developersbreach.cutepuppies.ui.theme.LalaHairColor
import com.developersbreach.cutepuppies.ui.theme.LuluEyeColor
import com.developersbreach.cutepuppies.ui.theme.LuluHairColor
import com.developersbreach.cutepuppies.ui.theme.MomoEyeColor
import com.developersbreach.cutepuppies.ui.theme.MomoHairColor
import com.developersbreach.cutepuppies.ui.theme.TtEyeColor
import com.developersbreach.cutepuppies.ui.theme.TtHairColor

object PuppyRepo {

    fun getCat(
        catId: Int,
        context: Context
    ): Cats = getCatsList(context).find {
        it.catId == catId
    }!!

    fun getCatsList(context: Context) = listOf(
        Cats(
            1,
            R.drawable.item_image_lulu,
            context.getString(R.string.cat_name_lulu),
            4,
            context.getString(R.string.cat_dob_lulu),
            context.getString(R.string.cat_gender_male),
            LuluHairColor,
            LuluEyeColor,
            context.getString(R.string.cat_description_lulu),
            context.getString(R.string.cat_breed_lulu),
            context.getString(R.string.cat_about_lulu),
        ),
        Cats(
            2,
            R.drawable.item_image_lala,
            context.getString(R.string.cat_name_lala),
            4,
            context.getString(R.string.cat_dob_lala),
            context.getString(R.string.cat_gender_female),
            LalaHairColor,
            LalaEyeColor,
            context.getString(R.string.cat_description_lala),
            context.getString(R.string.cat_breed_lala),
            context.getString(R.string.cat_about_lala),
        ),
        Cats(
            3,
            R.drawable.item_image_dd,
            context.getString(R.string.cat_name_dd),
            6,
            context.getString(R.string.cat_dob_dd),
            context.getString(R.string.cat_gender_male),
            DdHairColor,
            DdEyeColor,
            context.getString(R.string.cat_description_dd),
            context.getString(R.string.cat_breed_dd),
            context.getString(R.string.cat_about_dd),
        ),
        Cats(
            4, R.drawable.item_image_tt, context.getString(R.string.cat_name_tt), 6,
            context.getString(R.string.cat_dob_tt),
            context.getString(R.string.cat_gender_female),
            TtHairColor,
            TtEyeColor,
            context.getString(R.string.cat_description_tt),
            context.getString(R.string.cat_breed_tt),
            context.getString(R.string.cat_about_tt),
        ),
        Cats(
            5,
            R.drawable.item_image_chuchu,
            context.getString(R.string.cat_name_chuchu),
            4,
            context.getString(R.string.cat_dob_chuchu),
            context.getString(R.string.cat_gender_female),
            ChuchuHairColor,
            ChuchuEyeColor,
            context.getString(R.string.cat_description_chuchu),
            context.getString(R.string.cat_breed_chuchu),
            context.getString(R.string.cat_about_chuchu),
        ),
        Cats(
            6,
            R.drawable.item_image_coco,
            context.getString(R.string.cat_name_coco),
            5,
            context.getString(R.string.cat_dob_coco),
            context.getString(R.string.cat_gender_male),
            CocoHairColor,
            CocoEyeColor,
            context.getString(R.string.cat_description_coco),
            context.getString(R.string.cat_breed_coco),
            context.getString(R.string.cat_about_coco),
        ),
        Cats(
            7,
            R.drawable.item_image_momo,
            context.getString(R.string.cat_name_momo),
            5,
            context.getString(R.string.cat_dob_momo),
            context.getString(R.string.cat_gender_male),
            MomoHairColor,
            MomoEyeColor,
            context.getString(R.string.cat_description_momo),
            context.getString(R.string.cat_breed_momo),
            context.getString(R.string.cat_about_momo),
        ),
    )
}
