# 注意！这是可执行的jar文件
# getSCGoodsData
  **这是用于从星际公民游戏本地文件`Data.p4k`中检索物品(主要是船)售卖信息的简单程序(脚本)。**</br>  This jar software can search Goods(such as ships) info from the game data.</br></br>**需要依赖 ~java1.8、[unp4k](https://github.com/dolkensp/unp4k)和unforge；你可以在`dev`中找到已经打包好的unp4k和unforge程序。**</br>Before start, you should get ~java1.8,  [unp4k](https://github.com/dolkensp/unp4k) and unforge, Now you can easy to download unp4k and unforge in `dev`</br></br>  **当然，我更希望您可以前往 [unp4k在github的官方库](https://github.com/dolkensp/unp4k) 给原作者一个star**</br>  Of course, I wish you can learn more in [unp4k official lib on Github](https://github.com/dolkensp/unp4k), and don't forget to give a star</br></br>  **目前只能在 `≥windows7 64` 上运行。**</br>**only runable in `≥windows7 64`.**
  
  ### 注意：
  在获取全部飞船数据时会遍历以下数组，但数组是写死的，无法覆盖最新更新的载具；可以尝试自行更改以下数组或使用单个飞船查询功能而不是获取全部数据
  ```
  String[] keywordSTR = {"ptv","GRIN_ROC","buccaneer","dragonfly","cutlass_black","cutlass_red","cutlass_blue","caterpillar","herald","cyclone","cyclone_aa","cyclone_rc","cyclone_rn","cyclone_tr","mpuv","mpuv_Transport","mole","blade","prowler","prospector","razor","razor_ex","razor_lx","freelancer_dur","freelancer_MAX","freelancer_mis","freelancer","reliant","reliant_mako","reliant_sen","reliant_tana","starfarer","starfarer_gemini","c8x","Hornet_F7C","f7cs","f7cr","f7cm","hurricane","ANVL_Arrow","gladiator","hawk","terrapin","ANVL_Valkyrie","carrack","ballista","aurora_ln","aurora_cl","aurora_es","aurora_mr","aurora_lx","mantis","andromeda","aquila","phoenix","ursa_rover","avenger_titan","avenger_stalker","avenger_warlock","vanguard_sentinel","vanguard_harbinger","vanguard","vanguard_hoplite","hammerhead","gladius","sabre","eclipse","retaliator","reclaimer","ORIG_m50","ORIG_85X","100i","125a","135c","300i","315p","325a","350r","600i","600i_tour","890jump","mustang_alpha","mustang_beta","mustang_gamma","mustang_delta","nox","XIAN_Scout","p52_merlin","defender"};
  ```
      
      
      
---


## Version & Download
   [Get Donwload Link](https://github.com/cfdxkk/getSCGoodsData/wiki/Version-&-Download)

---
  
## 开始-start
- **下载jar**</br>download jar
- **打开cmd并导航到有getSCGoodsData.jar的文件夹**</br>open the command and goto the folder which have the getSCGoodsData.jar
- **输入 `java -jar GetSCGoodsData.jar` 然后跟着程序的指示操作**</br>type `java -jar GetSCGoodsData.jar` and follow the tips in the software

  
