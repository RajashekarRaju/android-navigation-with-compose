/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.data

import android.content.Context
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.model.Cats
import com.example.androiddevchallenge.ui.theme.ChuchuEyeColor
import com.example.androiddevchallenge.ui.theme.ChuchuHairColor
import com.example.androiddevchallenge.ui.theme.CocoEyeColor
import com.example.androiddevchallenge.ui.theme.CocoHairColor
import com.example.androiddevchallenge.ui.theme.DdEyeColor
import com.example.androiddevchallenge.ui.theme.DdHairColor
import com.example.androiddevchallenge.ui.theme.LalaEyeColor
import com.example.androiddevchallenge.ui.theme.LalaHairColor
import com.example.androiddevchallenge.ui.theme.LuluEyeColor
import com.example.androiddevchallenge.ui.theme.LuluHairColor
import com.example.androiddevchallenge.ui.theme.MomoEyeColor
import com.example.androiddevchallenge.ui.theme.MomoHairColor
import com.example.androiddevchallenge.ui.theme.TtEyeColor
import com.example.androiddevchallenge.ui.theme.TtHairColor

object PuppyAdoptionRepo {

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
