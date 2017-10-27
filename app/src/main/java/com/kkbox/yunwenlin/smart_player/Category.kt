package com.kkbox.yunwenlin.smart_player

class Category {
    inner class MoodCategory {
        var workOut = arrayOf("慢跑", "運動", "激勵", "鼓舞", "振奮", "節奏", "健身", "鍛鍊", "律動")
        var party = arrayOf("狂歡", "派對", "動感", "電音", "電子", "瘋狂", "跳舞", "搖滾", "嘻哈", "快歌")
        var relaxing = arrayOf("輕鬆", "愉悅", "輕音樂", "純旋律", "大自然", "爵士", "舒緩", "自在", "溫和", "寧靜", "冥想", "睡前歌曲", "舒眠", "夢鄉", "鋼琴", "淨化")
        var working = arrayOf("專注", "激昂", "振奮", "凝聚", "沈靜心緒", "保持清醒", "集中精神", "靈感")
        var romantic = arrayOf("告白", "求婚", "熱戀", "曖昧", "單戀", "愛", "感情", "初戀", "回憶", "暖心", "情歌", "浪漫", "祝福")
        var vacation = arrayOf("快樂", "出遊", "旅行", "度假", "陪伴", "解壓", "冒險")
        var cillOut = arrayOf("下午茶", "輕音樂", "放鬆", "溫暖", "愉悅", "談天", "古典", "寧靜午後")
        var tipsyNight = arrayOf("輕柔", "放鬆", "解壓", "愉悅", "藍調", "爵士")
        var acousticPop = arrayOf("輕快", "鄉村", "新鮮", "活潑", "自然", "吉他", "慢歌", "合音")
        var hardcore = arrayOf("重金屬", "搖滾", "電子電音", "電吉他", "喧鬧", "熱血", "吵雜")

        var mood = arrayOf(workOut, party, relaxing, working, romantic, vacation, cillOut, tipsyNight, acousticPop, hardcore)

        // to match any mood
        fun matchMood(input: String): String {
            for (i in mood.indices) {
                for (j in mood[i].indices) {
                    if (input.contains(mood[i][j])) {
                        return MainActivity.moodStation.getMoodStationId(i)
                    }
                }
            }
            return "error"
        }
    }

    inner class GenreCategory {
        val mandarinType = arrayOf("華語", "中文", "國語")
        val westernType = arrayOf("西洋", "英文", "英語", "英國", "美國")
        val japaneseType = arrayOf("日語", "日文", "日系", "日本")
        val koreanType = arrayOf("韓語", "韓文", "韓系", "韓國")
        val hokkienType = arrayOf("台語")
        val cantoneseType = arrayOf("粵語", "香港")
        val hipHopType = arrayOf("嘻哈", "藍調", "R and B")
        val rockType = arrayOf("搖滾")
        val electronicType = arrayOf("電子")
        val jazzType = arrayOf("爵士")
        val classicalType = arrayOf("古典")
        val backgroundType = arrayOf("背景", "情境")
        val worldMusicType = arrayOf("世界音樂")
        val religiousType = arrayOf("宗教")
        val kidsType = arrayOf("兒童", "兒歌", "童謠")
        val festivalType = arrayOf("節慶", "節日")

        var genreType = arrayOf(mandarinType, westernType, japaneseType, koreanType, hokkienType,
                cantoneseType, hipHopType, rockType, electronicType, jazzType, classicalType,
                backgroundType, worldMusicType, religiousType, kidsType, festivalType)

        // mandarin
        var mandarinNew = arrayOf("TYq3EHFTl-1EOvJM5Y", "最新", "新")
        var mandarinMale = arrayOf("SrGnPsRNdLGo2-QD4_", "男歌手", "男")
        var mandarinFemale = arrayOf("KmH_kXscZ40s9YgvHo", "女歌手", "女")
        var mandarinGroup = arrayOf("WoqzVWL-LKrtawiYGT", "樂團", "團體")
        var mandarinDuet = arrayOf("DZFgBL_02ygfJq12I0", "男女對唱", "對唱")
        var mandarinIdol = arrayOf("Wmm-aBHQWjQ0swnWFc", "偶像", "活力")
        var mandarinLoveSong = arrayOf("CrUb2t-gr2k6sxUxH5", "情歌", "精選情歌")
        var karaokeHits = arrayOf("OlKxmKz5tfab39jlx_", "KTV",  "熱門", "火紅")
        var goldenAward = arrayOf("LaN6zM2q9ZeLxC790i", "金曲獎")
        var mandarinFilmTV = arrayOf("Wmn-aBHQWjQ0vtJ5mX", "電影", "偶像劇", "電視劇")
        var mandarinRB = arrayOf("GkZPU3b9idZ5Ywz2mh", "R and B", "藍調", "節奏")
        var mandarinHipHop = arrayOf("9Y3bpZGZD0ROvGMjX6", "嘻哈", "饒舌")
        var mandarinRock = arrayOf("9Y3bpZGZD0ROvGMjX6", "搖滾", "熱血")
        var mandarinFolk = arrayOf("SpN0do2PFgM-_kRJwh", "民謠", "清新")
        var mandarinArtist = arrayOf("8t7mg6mQeMxxOLu7Co", "老歌", "歷久不衰")
        var mandarin70 = arrayOf("4m36EIQRNj51w-RhBT", "民歌", "校園")
        var rockRecord = arrayOf("KsuDexrbvM-yiqOLlC", "滾石唱片")
        var mandarin80 = arrayOf("TXaBf2-wa7qnHNbhzR", "80年代", "復古")
        var mandarin90 = arrayOf("GoozuamVg21P-KzsP5", "90年代", "經典")
        var hakkaHits = arrayOf("PYUgwVZUcnNgBfc2BM", "客家", "客語")
        var mandarin = arrayOf(mandarinNew, mandarinMale, mandarinFemale, mandarinGroup, mandarinDuet,
                mandarinIdol, mandarinLoveSong, karaokeHits, goldenAward, mandarinFilmTV,
                mandarinRB, mandarinHipHop, mandarinRock, mandarinFolk, mandarinArtist,
                mandarin70, rockRecord, mandarin80, mandarin90, hakkaHits)

        // western
        var westernNew = arrayOf("D-PqStdqi6B5ENvENn", "最新", "新")
        var westernLoveSong = arrayOf("KqmHNuNNCewkUXl1aI", "情歌", "精選情歌")
        var westernSoftPop = arrayOf("-pY6XHILXjYb01a3gW", "慢歌", "抒情")
        var billBoard = arrayOf("CrCL2t-gr2k6vXMgIa", "美國告示牌")
        var UKOfficial = arrayOf("DYCe_uDHLyKjziseIG", "英國金榜")
        var westernGroup = arrayOf("0oa9w7QPelazSTzoPe", "團體")
        var westernMovie = arrayOf("OrF-biXYtGjdSwDIbB", "電影", "原聲帶")
        var grammyAward = arrayOf("KqiXNuNNCewkUWTr9m", "葛萊美獎")
        var classicCountry = arrayOf("TXHhf2-wa7qnGZ1N9j", "鄉村")
        var westernOldies = arrayOf("4rjZ7_ak7KamnuvRhn", "老歌", "懷舊", "復古")
        var western60 = arrayOf("9XLuZm_oYAkPMyehPe", "60年代")
        var western70 = arrayOf("Go0zuamVg21P_r0WOi", "70年代")
        var western80 = arrayOf("CnVuyz4BRmmuel-ih", "70年代")
        var western90 = arrayOf("KlFFgpqRim2Mai4TFs", "90年代")
        var westernHits = arrayOf("1-iqhJs3e3ehOa9to7", "熱門", "火紅", "熱銷")
        var western = arrayOf(westernNew, westernLoveSong, westernSoftPop, billBoard, UKOfficial,
                westernGroup, westernMovie, grammyAward, classicCountry, westernOldies,
                western60, western70, western80, western90, westernHits)

        // Japanese
        var animeSongs = arrayOf("WrekE3lt0Dir37yqJ4", "動畫", "漫畫", "動漫")
        var japaneseRB = arrayOf("P-8J-NWKSC_82VFFSZ", "R and B", "藍調", "節奏")
        var japaneseNew = arrayOf("GsoDbQBt-3d_0MLNje", "最新", "新")
        var japaneseHits = arrayOf("GpoUaKI6NAET94De1R", "熱門", "火紅", "熱銷")
        var voiceAction = arrayOf("-sMWYY1mkJ_uqZtB8N")
        var japaneseFilmTV = arrayOf("0kyamvZvt1XFkHjMUs", "日劇", "電影", "電視")
        var japaneseLoveSong = arrayOf("8kkApbBC4gqLcro6Vj", "情歌", "精選情歌")
        var japaneseHipHop = arrayOf("Ko87FqHSeInLHgT2KU", "嘻哈", "饒舌")
        var easyListening = arrayOf("HY5ep-_Y8Fh6pUJgJU", "清新", "療癒", "自然")
        var japaneseRock = arrayOf("4rU57_ak7KamkCcMjo", "搖滾", "熱血")
        var japaneseElectronic = arrayOf("OkxUMhmweNQdLZ-XMw", "電子", "電音", "舞曲")
        var vocaloid = arrayOf("4sxrzNVesKrHPBk0wZ")
        var enka = arrayOf("5aeySnQ-YWXvrEt38A")
        var japaneseGirl = arrayOf("KsFDexrbvM-ygST6p_", "女團體", "女")
        var japanese7080 = arrayOf("4rVp7_ak7KammNhKKY", "70年代", "70年代")
        var japanese90 = arrayOf("D_bj6YfWDweKV1i7ch", "90年代")
        var visualRock = arrayOf("Pa2slhdsfCs-hB-D2q")
        var japanese = arrayOf(animeSongs, japaneseRB, japaneseNew, japaneseHits, voiceAction,
                japaneseFilmTV, japaneseLoveSong, japaneseHipHop, easyListening, japaneseRock,
                japaneseElectronic, vocaloid, enka, japaneseGirl, japanese7080,
                japanese90, visualRock)

        // korean
        var koreanNew = arrayOf("4pRYHcPw6CSeJMrdma", "最新", "新")
        var koreanHits = arrayOf("OlRRmKz5tfab2bq2MK", "熱門", "火紅", "熱銷")
        var koreanFilmTV = arrayOf("XZJErq6_md1MtKuG0j", "韓劇", "電影", "電視")
        var koreanBoy = arrayOf("Gnomhe3Rywt9hfFXNE", "男團體", "男")
        var koreanGirl = arrayOf("5-Gki7hI_xfvUrQWqj", "女團體", "女")
        var koreanDance = arrayOf("SmtqOuJy3MHUiVphDZ", "舞曲")
        var koreanIndiePop = arrayOf("DY0u3uDHLyKjzIalv4", "清新", "自然", "乾淨")
        var koreanLoveSong = arrayOf("4olkAfwrwLtSGQrOOr", "情歌", "精選情歌")
        var korean = arrayOf(koreanNew, koreanHits, koreanFilmTV, koreanBoy, koreanGirl,
                koreanDance, koreanIndiePop, koreanLoveSong)

        // hokkien
        var hokkienMusic = arrayOf("9ZAb9rkyd3JFDBC0wF", "熱門", "火紅", "熱銷")
        var hokkienMale = arrayOf("OqePQq9vDcnuUokGf1", "男歌手", "男")
        var hokkienFemale = arrayOf("DYc-_uDHLyKjx-OgHU", "女歌手", "女")
        var hokkienDuet = arrayOf("9ax4APKMtg7NqOqhNp", "男女對唱", "對唱")
        var hokkienOldies = arrayOf("LYGtlGyhA3ktP0fQq2", "老歌", "懷舊", "復古")
        var hokkien = arrayOf(hokkienMusic, hokkienMale, hokkienFemale, hokkienDuet, hokkienOldies)

        // cantonese
        var cantoneseMale = arrayOf("TajEeNViAL9ZnNGJUV", "男歌手", "男")
        var cantoneseFemale = arrayOf("4rxZz_ak7Kamnyuc8B", "女歌手", "女")
        var cantoneseGroup = arrayOf("D_8zyYfWDweKUm19RW", "樂團", "團體")
        var cantoneseIndie = arrayOf("GpzUSKI6NAET_QMZym")
        var cantoneseLoveSong = arrayOf("-oP3NMPHl2xW3Mrk3g", "情歌", "精選情歌")
        var HKTV = arrayOf("Kla1opqRim2Mb2h801", "港劇", "港片")
        var cantonese = arrayOf(cantoneseMale, cantoneseFemale, cantoneseGroup, cantoneseIndie, cantoneseLoveSong, HKTV)


        // hip-hop/R&B
        var hipHopHits = arrayOf("4meqEIQRNj51ziMia_", "熱門", "火紅", "熱銷")
        var USRap = arrayOf("LXN77VlRwlsDThKaty", "美式饒舌", "rap")
        var urbanSoul = arrayOf("Ct4MuGxwIUFoqWWkTx", "都會靈魂")
        var reggae = arrayOf("Ko5bFqHSeInLEVUfjV", "雷鬼")
        var otherHipHop = arrayOf("4kP0AvQT089ychjEAw", "異國嘻哈")
        var hipHopDisco = arrayOf("H_eMSoUEa53s-AuMR9", "迪斯可", "放克")
        var hipHopClassic = arrayOf("4s27zNVesKrHPRBBE4", "靈魂樂")
        var RB = arrayOf("Covu0zzHhuRtK_ctj_", "R and B", "藍調", "節奏")
        var hipHop = arrayOf(hipHopHits, USRap, urbanSoul, reggae, otherHipHop,
                hipHopDisco, hipHopClassic, RB)

        // rock
        var modernRock = arrayOf("5-MUi7hI_xfvXKDcGn", "熱門", "火紅", "熱銷")
        var numetal = arrayOf("DZpQBL_02ygfJDvc6Z", "無金屬")
        var heavyMetal = arrayOf("0rPSAjlTQEfKPvfcNp", "重金屬", "爆發性", "硬式搖滾")
        var otherRock = arrayOf("8oDzPzAAeM8PUyD2Uo", "另類搖滾")
        var britiahRock = arrayOf("CldzDhywhAvUpzGBRy", "英式搖滾")
        var punkRock = arrayOf("Kkz6QCSU_3_CBPqBZl", "龐克搖滾")
        var softIndiePop = arrayOf("WmIOaBHQWjQ0srfxb_", "清新", "自然", "乾淨")
        var greatGuitar = arrayOf("Or6ObiXYtGjdS6oEWt", "吉他手")
        var rock6070 = arrayOf("X-h-RZu9ySjRSyF7yY", "老搖滾", "60年代", "70年代")
        var rockBalled = arrayOf("_a3_Bzt1poJ2BR3JKa", "情歌", "精選情歌")
        var rock = arrayOf(modernRock, numetal, heavyMetal, otherRock, britiahRock,
                punkRock, softIndiePop, greatGuitar, rock6070, rockBalled)

        // electronic
        var electronicMix = arrayOf("Gqe61mgMEwwhsuo-GB", "電音", "混音")
        var danceHits = arrayOf("Sq9l5wk0QAfiZel09a", "熱門", "火紅", "熱銷")
        var drumBass = arrayOf("0m079z5FITNUNM-Eup", "鼓", "貝斯")
        var tripHop = arrayOf("5Z0rFoY1HDCNrL6evY", "神遊舞曲", "Trip Hop")
        var bigBeat = arrayOf("KtX4lqwazkenXkAnos", "碎拍" ,"Big Beat", "Breakbeat")
        var loungeBar = arrayOf("KpNiH4nH4ZXaeovvxD", "Lounge bar")
        var electronicDisco = arrayOf("LXG77VlRwlsDTGKqjX", "迪斯可", "Disco")
        var techno = arrayOf("Com-0zzHhuRtI8yQcj", "Techno", "Trance")
        var house = arrayOf("8sWaKNck-Ut-wz78PJ", "House", "舒緩電子樂", "放鬆電子樂")
        var electronic = arrayOf(electronicMix, danceHits, drumBass, tripHop, bigBeat,
                loungeBar, electronicDisco, techno, house)

        // jazz
        var jazzCollection = arrayOf("8nfTXPSzsZ73WE1Sd-", "熱門", "火紅", "熱銷", "精選")
        var smoothJazz = arrayOf("9aOYAPKMtg7NrYpBON", "輕鬆")
        var vocalJazz = arrayOf("KovbFqHSeInLE8vqpD", "演唱輯", "演唱")
        var instrumentalJazz = arrayOf("_ZRffYpizZlv2KuuIT", "演奏輯", "演奏")
        var bossaNova = arrayOf("OoHbrlKIHkfI3Ybq1h", "巴薩諾瓦", "bossa nova")
        var blues = arrayOf("1_XIXZ9JSJeh2n7gK2", "藍調")
        var bigBand = arrayOf("5_JqALFhjm_nm-nD4P", "大樂隊", "搖擺樂", "樂團")
        var bop = arrayOf("OrmubiXYtGjdRQHDoQ", "咆勃", "硬咆勃")
        var coolJazz = arrayOf("8kNgpbBC4gqLexTs5K", "酷派", "西岸爵士")
        var fusion = arrayOf("HZD2dVpt8lYQfx3BI9", "融合爵士", "跨界爵士")
        var freeJazz = arrayOf("Co_u0zzHhuRtLJRUSQ", "自由爵士")
        var newOleansJazz = arrayOf("Co-e0zzHhuRtKurrXC", "紐奧良爵士")
        var soulJazz = arrayOf("-p8qXHILXjYb3A8Bxs", "靈魂爵士", "放克")
        var latinJazz = arrayOf("4ngZ5mqkqyTudDHRb_", "拉丁爵士")
        var standardJazz = arrayOf("0sck9GvrrgrIU8D2Iw", "爵士標準曲")
        var penguinJazz = arrayOf("GqLa1mgMEwwhvIUCuQ", "企鵝")
        var milesDavis = arrayOf("Cow-0zzHhuRtLvn67b", "Miles Davis")
        var johnColtrane = arrayOf("OsCqr1hwVS3wyByIk1", "John Coltrance")
        var pianoJazz = arrayOf("PatMthdsfCs-heAxmG", "鋼琴")
        var trumpetJazz = arrayOf("DYtO_uDHLyKjy6wWoj", "小號")
        var guitarJazz = arrayOf("9aB4APKMtg7NrpxBwA", "吉他")
        var saxophoneJazz = arrayOf("8tMWg6mQeMxxM71KOv", "薩克斯風")
        var jazz = arrayOf(jazzCollection, smoothJazz, vocalJazz, instrumentalJazz, bossaNova,
                blues, bigBand, bop, coolJazz, fusion,
                freeJazz, newOleansJazz, soulJazz, latinJazz, standardJazz,
                penguinJazz, milesDavis, johnColtrane, pianoJazz, trumpetJazz,
                guitarJazz, saxophoneJazz)

        // classical
        var famousClassical = arrayOf("GsLDbQBt-3d_1j3ym2", "熱門", "名曲", "精選")
        var berlinOrchestra = arrayOf("Gp2EeKI6NAET9u_DiL", "柏林愛樂", "樂團")
        var mozart = arrayOf("P_lC_BY2HMZW_c2KVd", "莫札特")
        var beethoven = arrayOf("P-gJ6NWKSC_80-mTA9", "貝多芬")
        var schubert = arrayOf("0mbL5z5FITNUM4_uqr", "舒伯特")
        var chopin = arrayOf("5a-SWnQ-YWXvrY_U5_", "蕭邦")
        var bach = arrayOf("DahUfzKyjIxavQB1RS", "巴哈")
        var tchaikovsky = arrayOf("D-caWtdqi6B5HQg0WX", "柴可夫斯基")
        var classicalVocal = arrayOf("D_6D-YfWDweKUdVf6u", "古典美聲")
        var crossOverVocal = arrayOf("4r3p__ak7KamnJO-X5", "跨界美聲")
        var opera = arrayOf("8npzTPSzsZ73Vahws2", "歌劇")
        var baroque = arrayOf("ClyjHhywhAvUorf7ue", "巴洛克")
        var classicalPeriod = arrayOf("0oId07QPelazSsVfqp", "古典樂派")
        var romanticEra = arrayOf("TYvXAHFTl-1EPsoRYX", "浪漫樂派")
        var modernClassical = arrayOf("LaP63M2q9ZeLzCOShG", "現代樂派")
        var nationalistic = arrayOf("CnFu2z4BRmmufbUEo4", "國民樂派")
        var impressionism = arrayOf("TXZRb2-wa7qnFvp2-a", "印象樂派")
        var classicalPiano = arrayOf("TavUSNViAL9ZlU1jKd", "鋼琴")
        var classicalViolin = arrayOf("_YTydjFAzbhKGyV9td", "小提琴")
        var classicalCello = arrayOf("Gp_EeKI6NAET9J_zsu", "大提琴")
        var conductors = arrayOf("GkUPQ3b9idZ5abVSrM", "指揮家")
        var classical = arrayOf(famousClassical, berlinOrchestra, mozart, beethoven, schubert,
                chopin, bach, tchaikovsky, classicalVocal, crossOverVocal,
                opera, baroque, classicalPeriod, romanticEra, modernClassical,
                nationalistic, impressionism, classicalPiano, classicalViolin, classicalCello,
                conductors)

        // background
        var chillOut = arrayOf("CtWcqGxwIUFopY83Dv", "舒壓", "輕鬆", "放鬆")
        var tropicalHolidays = arrayOf("8ro7a5mZ4CO1iv7HUf", "熱帶", "南國", "南國渡假")
        var riseAndShine = arrayOf("4txLNI125HMtN5Pk1Y", "早晨", "清晨", "早上")
        var whistle = arrayOf("-q3h3Ax7BJiShKFisb", "工作")
        var teatTime = arrayOf("X_cttqWSUF2yWepEGC", "午茶", "約會")
        var romanticDinner = arrayOf("_-Z--OPV79akIfK-NS", "晚餐", "浪漫")
        var lateNight = arrayOf("L-h7I0qO82JDk7zdBP", "半夜", "寧靜", "安靜")
        var afterWorkCafe = arrayOf("KqknJuNNCewkVvN_lq", "下班", "酒館", "解壓")
        var revItUp = arrayOf("0of907QPelazQkcrS7", "High", "瘋狂")
        var discoSaturday = arrayOf("OpBR2_-l2xhlbDhfZV", "狂熱", "週末夜", "派對")
        var yoga = arrayOf("Otsjo2i6aDY_gvSPQY", "瑜珈", "冥想", "沈思")
        var workOut = arrayOf("Hara2Z8dBgLd9bNejn", "運動", "健身", "鍛鍊")
        var sunShine = arrayOf("GkIPQ3b9idZ5bmbP6f", "陽光", "沙灘", "海")
        var surfing = arrayOf("CtKMqGxwIUFor4jsDy", "衝浪")
        var sweetLove = arrayOf("LXxb_VlRwlsDTOzDRo", "情人", "情歌", "甜言蜜語")
        var chinesePop = arrayOf("PZjZjmp6QVfCuHwCFR", "華語流行")
        var JKPop = arrayOf("9--arFvkUr94huXPco", "日韓流行")
        var movieOriginal = arrayOf("WtxKSbrClJ-rxVAKqO", "電影配樂", "電影插曲", "電影")
        var newAge = arrayOf("-prqTHILXjYb1ktlyQ", "新世紀")
        var baroqueRococo = arrayOf("4ppIDcPw6CSeJ3Q7YV", "歐式宮廷")
        var background = arrayOf(chillOut, tropicalHolidays, riseAndShine, whistle, teatTime,
                romanticDinner, lateNight, afterWorkCafe, revItUp, discoSaturday,
                yoga, workOut, sunShine, surfing, sweetLove,
                chinesePop, JKPop, movieOriginal, newAge, baroqueRococo)

        // world music
        var traChina = arrayOf("WsDW4y3-YTlKKtRJPG", "中國", "傳統")
        var chanson = arrayOf("GmCzMzrGUIbjLMZBut", "法式", "法國")
        var latin = arrayOf("St6Zl2ToWq92dK9FNG", "拉丁")
        var flamenco = arrayOf("8kdQtbBC4gqLegpyfQ", "佛朗明哥")
        var italy = arrayOf("Cm0fU5mRTWygljY5fG", "義大利")
        var caribbean = arrayOf("4s3L3NVesKrHOdltLE", "加勒比海", "熱帶風情")
        var hawaii = arrayOf("Lavq3M2q9ZeLyDr24K", "夏威夷")
        var mexican = arrayOf("KnGKUb9xoMDeeR94WN", "墨西哥")
        var argentina = arrayOf("Kl1VkpqRim2Mad3RVO", "阿根廷")
        var bali = arrayOf("5X_KNgSpkjF5tZSG-C", "印尼", "巴里島")
        var thailand = arrayOf("D_RT-YfWDweKWNNvWF", "泰國")
        var india = arrayOf("Gk3vQ3b9idZ5bQeIgl", "印度")
        var middleEast = arrayOf("KtQYhqwazkenWwjT05", "中東")
        var africa = arrayOf("8lgfOredbrirIpUU6M", "非洲")
        var worldMusic = arrayOf(traChina, chanson, latin, flamenco, italy,
                caribbean, hawaii, mexican, argentina, bali,
                thailand, india, middleEast, africa)

        // religious music
        var gospelHits = arrayOf("DaTkfzKyjIxasu3J4N", "福音")
        var taoist = arrayOf("OpjB2_-l2xhlbNor9E", "民間信仰")
        var buddhism = arrayOf("D-oKWtdqi6B5GAW7cU", "佛教")
        var greatSutra = arrayOf("-mGFXZnpWLE4Ah7YWI", "大悲咒")
        var heartSutra = arrayOf("DX0e_jo6-WHyxJfiEA", "心經")
        var sutraReciting = arrayOf("4q4kFmP_HO8lJeu5la", "佛經")
        var hokkienSutraReciting = arrayOf("SoqxM7TIXitqQCPHCV", "台語佛經")
        var religious = arrayOf(gospelHits, taoist, buddhism, greatSutra, heartSutra,
                sutraReciting, hokkienSutraReciting)

        // kids music
        var disney = arrayOf("PY-A0VZUcnNgAoLW9R", "迪士尼")
        var kidSongs = arrayOf("L_bosKWZ-IuNSatvEW", "西洋")
        var kidClassic = arrayOf("P_fS_BY2HMZW8b6blH", "經典")
        var lullaby = arrayOf("TYT3AHFTl-1EMf7IAK", "搖籃曲", "安眠曲")
        var kidPop = arrayOf("CqrC3kny7nlTXy5F1p", "流行")
        var kids = arrayOf(disney, kidSongs, kidClassic,lullaby,kidPop)

        // festival music
        var lunarNewYear = arrayOf("OrvufiXYtGjdSTFtbr", "新年", "農曆新年")
        var festival = arrayOf(lunarNewYear)

        var genre = arrayOf(mandarin, western, japanese, korean, hokkien,
                cantonese, hipHop, rock, electronic, jazz,
                classical, background, worldMusic, religious, kids,
                festival)

        fun checkGenreType(input: String): Int {
            for (i in genreType.indices) {
                for (j in genreType[i].indices) {
                    if (input.contains(genreType[i][j])) {
                        return  i
                    }
                }
            }
            return -1
        }

        fun matchGenre(input: String, type: Int): String {
            println("genre type: " + type.toString())
            for (subcategory in genre[type]) {
                for (i in 1..subcategory.size-1) {
                    if (input.contains(subcategory[i])) {
                        return subcategory[0]
                    }
                }
            }
            return "error"
        }
    }

    var moodCategory = MoodCategory()
    var genreCategory = GenreCategory()
}