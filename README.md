# 256702-F2-Team13-345-507
## Inferno & Tide


## สมาชิก

1. นายพงศกร เกศนาคินทร์ 6730300345
2. นายรพีพงศ์ ธนปกรณ์เกียรติ 6730300507

## รายละเอียดโดยย่อ

**คำแนะนำ**

- โครงงานนี้เป็นเกมแนวป้องกันฐาน(tower defense)จากศัตรูที่มาบุกฐาน


**การเขียน (เกม)** 

จำลองผู้เล่นเป็นพระเจ้าควบคุมไฟเพื่อไม่ให้ลุกลามเพื่อป้องกันภยันอันตรายที่อาจเกิดขึ้นโดยสร้างน้ำในรูปแบบต่างๆเพื่อหยุดไฟแล้วป้องกันฐาน โดยเมื่อผ่านด่านได้จะได้รับสิ่งตอบแทนเพื่อพัฒนาตัวละครน้ำของเรา เพื่อให้ป้องกันให้ได้มากขึ้นเพื่ออนาคตของโลกใบนี้

## คุณลักษณะและขอบเขต

- เมื่อมอนไฟโดนน้ำยิงจะเลือดหมดจะต้องตายแล้วหายไป
- เมื่อมอนไฟเดินเข้าฐานจนเลือดหมดผู้เล่นจะแพ้
- เมื่อผู้เล่นเอาชีวิตจนจบได้จะชนะ

### คุณลักษณะขั้นต่ำ

- มีศัตรูเป็นไฟ
- มีน้ำเป็นทหารป้องกัน
- มีเส้นทางการเดิน
- มีฐาน

**ตัวอย่างการเขียนสำหรับ Inferno & Tide**

- ตัวละครหลักเป็นพระเจ้่า ซึ่งสามารถควบคุมทหารน้ำและวางในจุดต่างๆของแผนที่เพื่อยิงกระสุนใส่ศัตรู
- มีไฟซึ่งเมื่อชนฐานแล้วจะเสียพลังชีวิต และหากพลังชีวิตหมดจะแพ้
- มีระบบจับเวลาที่ใช้ในการผ่านด่านนั้นๆ
- มีด่านที่สามารถเล่นได้อย่างน้อย 2 ด่าน
- มีระบบพัฒนาความสามารถของผู้เล่น ยกตัวอย่างเช่น ตัวละครใหม่ๆที่โหดขึ้น อัพเกรดตัวละครให้เก่งขึ้น

### คุณลักษณะเพิ่มเติม

- สามารถพัฒนาตัวละครได้
- ด้านพื้นหลังมีการขยับ
- มีการอัพเดตสถานะของมอนตลอดเวลา
- หลังจากเล่นจบจะมีเวลาเล่นไปเท่าไหร่

## แผนการดำเนินโครงงาน

ร่างแผนการดำเนินโครงงานในระยะเวลา 2 เดือน (8 สัปดาห์) โดยระบุผลลัพธ์ที่คาดหวังว่าจะทำได้เสร็จในแต่ละระยะ เช่น

| **ระยะ**        | **ช่วงเวลา**   | **ผลลัพธ์ที่คาดหวัง**                                                                            |
| --------------- | -------------- | ------------------------------------------------------------------------------------------------ |
| เริ่มต้นโครงงาน | สัปดาห์ที่ 1-2 | - ส่งข้อเสนอโครงงาน<br>- สร้าง GitHub repository<br>- สร้างโครงงานด้วย Maven และเขียนโค้ดตั้งต้น |
| สร้างต้นแบบ     | สัปดาห์ที่ 3-4 | - โปรแกรม/เกมทำงานได้ในเบื้องต้น<br>- มีฟีเจอร์หลัก (core feature) ที่ใช้งานได้                  |
| ทดลองใช้งาน     | สัปดาห์ที่ 5-6 | - ฟีเจอร์ส่วนใหญ่พร้อมใช้งาน<br>- โปรแกรมสามารถใช้งานได้ แต่อาจต้องปรับแก้หรือเพิ่มเติม          |
| พร้อมส่งมอบงาน  | สัปดาห์ที่ 7-8 | - โปรแกรม/เกมมีฟีเจอร์ครบถ้วนตามที่วางแผน<br>- เอกสารประกอบสมบูรณ์ พร้อมสำหรับการนำเสนอ          |
**หมายเหตุ**

- ฟีเจอร์หลัก (core feature) หมายถึงฟีเจอร์สำคัญที่ทำให้โปรแกรม/เกมบรรลุวัตถุประสงค์หลัก
- MVP (Minimum Viable Product) คือเป้าหมายขั้นต่ำที่สามารถส่งมอบได้ อย่างไรก็ตาม ฟีเจอร์หลักอาจเป็น subset ของ MVP ได้ในบางกรณี

## การแบ่งงาน

ระบุแผนการแบ่งความรับผิดชอบของสมาชิกแต่ละคนในการทำโครงงาน ทั้งนี้ สมาชิกทุกคนต้องมีส่วนร่วมในการเขียนโค้ด เช่น

การแบ่งงาน

    นายพงศกร เกศนาคินทร์ 
        จัดทำเอกสาร 
        ออกแบบ UX UI
        จัดการ Git Respositary

    รพีพงศ์ ธนปกรณ์เกียรติ 
        ทำโมเดล
        เขียนโค้ดทดสอบระบบ
        เขียนโค้ดส่วนเกม


## ความท้าทายและความเสี่ยง

**คำแนะนำ**

- วิเคราะห์ล่วงหน้าถึงปัญหาที่อาจเกิดขึ้น ระบุให้ชัดเจนพร้อมแนวทางการแก้ไข
- อาจเป็นเรื่องของเทคนิค (เช่น การเรียนรู้ library ใหม่) หรือการจัดการเวลา

**ตัวอย่าง**

1. **ความท้าทายด้านเทคนิค**:
    - ไม่คุ้นชินกับเขียนโค้ดสร้างเกมและการออกแบบ
    - **แนวทางแก้ไข**: ศึกษาตัวอย่างและบทเรียนจากลิงก์ Pragmatic Coding (https://www.pragmaticcoding.ca/beginners/intro)
2. **ความท้าทายด้านการพัฒนาเกมด้วย FXGL**:
    - เป็นเกมที่ใช้ความแม่นยำสูงทั้งด้านการคำนวณระยะและเวลา 
    - **แนวทางแก้ไข**: ทำตาม tutorial ใน FXGL Wiki (https://github.com/AlmasB/FXGL/wiki/FXGL-11) เพื่อพัฒนาความเข้าใจ
3. **ความเสี่ยงด้านเวลา**:
    - ใช้เวลาอาจมากเกินที่กำหนดและแก้ไขเพิ่มเติม ทำให้ไม่ทันกำหนดการ
    - **แนวทางแก้ไข**: วางแผนฝึกทักษะคู่ไปกับการพัฒนาโครงงาน และติดตามความคืบหน้า (progress) ของตนเองทุกสัปดาห์

## ต้นแบบและเอกสารอ้างอิง

**คำแนะนำ**

- หากมีต้นแบบซอฟต์แวร์ (Prototype) หรือโค้ดตัวอย่างที่ใช้เป็นแบบอ้างอิง ให้ระบุชื่อโปรเจกต์/ลิงก์เอาไว้
- ใส่ข้อมูลแหล่งเรียนรู้ที่ใช้อ้างอิง เช่น เว็บไซต์ ตำรา หรือวิดีโอสอนต่าง ๆ

เอกสารอ้างอิง

- FXGL Tutorial: [https://github.com/AlmasB/FXGL/wiki/FXGL-11](https://github.com/AlmasB/FXGL/wiki/FXGL-11)
- JavaFX Documentation: [https://openjfx.io/](https://openjfx.io/)
- วิธีการจับการเคลื่อนไหวคน: https://stackoverflow.com/questions/23553604/how-to-track-an-object-during-animation
- มอนจะขยับยังไง https://stackoverflow.com/questions/30044870/tower-defense-game-moving-the-enemies-on-the-screen
- คำนวณวิถีกระสุน https://stackoverflow.com/questions/50255929/tower-defense-bullet-math

งานนำเสนอ
https://www.canva.com/design/DAGhauw1ILA/mFspp8oLcBjjJQ_yRsH56w/view?utm_content=DAGhauw1ILA&utm_campaign=designshare&utm_medium=link2&utm_source=uniquelinks&utlId=h51a1fb10b0

รายงาน
