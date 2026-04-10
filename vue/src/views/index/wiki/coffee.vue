<template>
    <el-main>
        <el-header>
            <div class="header">
                <p class="card-title-primary">
                    <span class="text">咖啡简介</span>
                </p>
            </div>
        </el-header>
        <div class="wiki-content">相关词条：</div>
        <div class="wiki-content">
            <el-row :gutter="20">
                <el-col :span="16">
                    <div class="content-text">
                        <p>
                            <b>咖啡</b>
                            （英语：coffee，源自荷兰语：koffie，借自奥斯曼土耳其语：kahve 和阿拉伯语：qahwah
                            قَهْوَة‎‎）是指将咖啡植物的种子（即咖啡豆）经过烘焙磨粉后冲泡溶解制成的饮料，是世界上流行范围最广泛的软性饮料之一。
                            咖啡做为大众饮品起源于15-16世纪，小果咖啡由埃塞俄比亚通过商贸传到也门后扩散至穆斯林世界。
                        </p>
                        <p>
                            野生咖啡原产于非洲和亚洲的热带地区，共有超过130个物种，但其中成为经济作物的只有两种，全部来自于撒哈拉以南非洲：模式种小果咖啡（Coffea
                            arabica）也称阿拉比卡咖啡（Arabica
                            coffee），源自东非的埃塞俄比亚西南高地，是最主要的咖啡品种，占据全球咖啡总产量的60～80%；抗病虫性更好、颗粒较粗、酸味较低但苦味较浓的中果咖啡（Coffea
                            canephora），也称罗布斯塔咖啡（robusta coffee），源自中非和西非，占全球总产量的20～40%。
                        </p>
                        <p>
                            咖啡做为大众饮品起源于15-16世纪，小果咖啡由埃塞俄比亚通过跨海贸易传到也门后扩散至穆斯林世界，
                            16世纪的威尼斯商人将咖啡从奥斯曼帝国引入意大利。随后17-18世纪由于欧洲对咖啡的需求，促使殖民者将咖啡树传播并栽种到南美洲、
                            东南亚和南亚等热带地区。现今，未经烘焙的生咖啡豆作为世界上最大的出口农产品，以及世界上交易量为广泛的热带农产品之一，
                            也是发展中国家出口中最有价值的商品之一，有超过70个国家种植咖啡树。采收的成熟咖啡果会经过剥离果肉的初步加工，
                            再经过烘焙的工序，而成为能制作咖啡的咖啡豆。透过不同的冲泡方式与成分比例，咖啡有浓缩咖啡、卡布奇诺和拿铁咖啡等变化。
                            一些争议指咖啡的种植与它环境影响有关，例如肯尼亚的咖啡豆在移植种植后会失去独有的肯尼亚酸，因为肯尼亚原种地土壤含有较高浓度的磷酸。
                            因此，公平贸易咖啡与有机咖啡是一个不断扩大的市场。
                        </p>
                    </div>
                    <div class="content-text">
                        <h2 class="heading">历史</h2>
                        <el-text class="mx-1" size="small"
                            >主要条目：<a
                                href="https://zh.wikipedia.org/wiki/%E5%92%96%E5%95%A1%E7%9A%84%E5%8E%86%E5%8F%B2"
                                >咖啡的历史</a
                            ></el-text
                        >
                        <p>
                            传说9世纪的埃塞俄比亚的牧羊人发现并咀嚼了咖啡果实，随后将咖啡果实带给了附近修道院的僧侣，但僧侣起初不愿食用果实，并把果实扔进火里，
                            经过火烤的咖啡果中冒出香气引来僧侣前来查看，僧侣从余烬中捞出咖啡豆，并将其磨碎溶解在热水中，这才制成了世界上第一杯咖啡。
                            但此故事截至1671年并没有得到任何记载，因此可能是杜撰的。亦有研究认为最初栽培的咖啡源自埃塞俄比亚的哈勒尔。埃塞俄比亚的阿克苏姆王国兴盛时曾一度占据也门南部，
                            6世纪中期，萨珊帝国攻占也门后将阿克苏姆赶出南阿拉伯半岛，可以肯定的是咖啡是从埃塞俄比亚传播到也门的。
                        </p>
                        <p>
                            咖啡传播到穆斯林世界后，伊斯兰医学认可了咖啡的好处，认为其可以提振精神并防止酒和大麻对穆斯林的诱惑，15世纪的也门苏菲派修道院在祈祷时使用咖啡来帮助集中注意力。
                            16世纪初，咖啡从也门的摩卡港传播到埃及，随后咖啡馆还出现在叙利亚阿勒颇，并于1554年在奥斯曼帝国首都伊斯坦布尔开业。
                            1511年，由于也门麦加的宗教领袖认为咖啡具有刺激作用，便开始禁止穆斯林饮用咖啡，造成其余阿拉伯世界的苏丹和宗教领袖也相继效仿；其中两位奥斯曼帝国苏丹更是同样出于政治考量，
                            而在1517年和1623年两度禁止咖啡。
                        </p>
                        <p>
                            同样在16世纪，与阿拉伯世界的贸易令威尼斯获得了包括咖啡在内的非洲商品，威尼斯商人则向威尼斯的上流阶级高价推销咖啡。起初意大利的宗教人士对咖啡这种穆斯林饮料持怀疑态度，
                            并称咖啡为“撒旦的苦涩发明（bitter invention of Satan）”或是“阿拉伯酒（wine of
                            Araby）”，1600年，教宗克莱孟八世对咖啡的争议作出裁决，在教宗品尝咖啡后认为可以饮用，
                            并祝福了咖啡。1616年，荷兰商人彼得·范登布罗克从也门摩卡获得了一些阿拉比卡咖啡树苗并带回了阿姆斯特丹，还在当地植物园种植成功。1658年，荷兰人首先在其殖民地锡兰和印度南部开始种植咖啡，
                            但出于避免供应过剩而降低价格的考量，最终放弃了在锡兰种植，专注于爪哇和苏里南的种植园。
                        </p>
                        <p>
                            1675年时，英格兰就有3000多家咖啡馆[26]；启蒙运动时期，咖啡馆成为民众深入讨论宗教和政治的聚集地，1670年代的英国国王查理二世就曾试图取缔咖啡馆。这一时期的英国人认为咖啡具有药用价值，甚至名医也会推荐将咖啡用于医疗。
                        </p>
                        <p>
                            1773年，波士顿倾茶事件后约翰·亚当斯和许多美国人认为喝茶是不爱国的，令大量美国人在美国独立战争期间改喝咖啡。
                        </p>
                        <p>
                            18世纪，葡萄牙人首先在巴西里约热内卢附近，后来则是圣保罗种植咖啡并建设种植园。1852-1950年，巴西主导了世界咖啡生产，其出口的咖啡比世界其他地区的总和还多。
                            1950年以来，由于哥伦比亚和越南等主要生产国相继出现，而越南在1999年超过哥伦比亚成为世界第二大咖啡生产国，并在2011年达到15%的市场份额，而同年巴西的市场份额仅占33%。
                        </p>
                        <p>
                            在咖啡的原产地埃塞俄比亚，18世纪前咖啡曾被埃塞俄比亚正教会所禁止，直至19世纪后期叶埃塞俄比亚皇帝孟尼利克二世的统治时期才有所开放。
                        </p>
                        <p>
                            咖啡在19世纪中已经引入中国上海，1843年—44年上海对外贸易文献就有记载“枷榧豆5包，每包70斤”，表明当时上海已经从外国进口咖啡豆。
                        </p>
                        <p>
                            香港英文报章在1866年刊登关于coffeeshop的报导。1885年香港中文报章以“咖啡”为中文名，此后逐渐成为华语地区普及使用的中文译名。
                            香港在1840年代起有英国人聚居，由于饮食文化的差异，最初被输入到香港的咖啡豆主要供应西方人饮用，而一般本地华人则不喜欢咖啡苦涩的味道，
                            在早年的香港常有大量从事搬运工作的苦力在码头聚集，为来港的货轮搬运货物，从事体力劳动的苦力比一般华人更容易接触到刚循海路进口的咖啡豆，所以在华人社会中最早有饮用咖啡习惯的群体，却是社会地位低下的码头搬运工。
                        </p>
                        <h2 class="heading">主要成分</h2>
                        <p>
                            蔗糖：烘焙过程中部分会热裂解为甲酸、醋酸、乳酸、甘醇酸，故含糖量愈高的生豆，在浅焙时会愈酸，
                            可增加咖啡香气复杂度；部分会在过程中经过焦糖化反应而变为焦糖，可增加咖啡风味。
                        </p>
                        <p>
                            咖啡因：咖啡的主要活性成分。略带苦味，熔点高达237℃，因此烘焙好的咖啡熟豆，其咖啡因几乎完整保留下来，
                            并在萃取时会融入杯中。咖啡因的作用主要是刺激循环系统和呼吸系统、中枢神经系统，特别是大脑皮质层的脑细胞，
                            适量的咖啡因亦可减轻肌肉疲劳，使人提高警惕性、情绪激昂、减轻睡意及肌肉疲劳，维持持久的工作能力。
                            亦能提高身体基础代谢，抗老化，具有利尿作用，并能促进消化液分泌刺激肠胃蠕动，帮助排便。
                            咖啡因还可以缩短人的反应时间并提高反应能力，因此在奥运会中被列为违禁品；但过量摄入咖啡因也会带来副作用，
                            使人失眠、烦躁、易怒；由于它会促进肾脏机能，有利尿作用，帮助体内将多余的钠离子排出体外，但摄取过多也会导致咖啡因中毒。
                        </p>
                        <p>
                            酚酸：<br />
                            &emsp;&emsp;绿原酸：浅焙至中焙（一爆至二爆）中，约有50%的绿原酸会降解为奎宁酸（酸涩），而咖啡亦是人类摄取绿原酸的主要来源。<br />
                            &emsp;&emsp;咖啡酸
                        </p>
                        <p>
                            脂肪：其中最主要的是酸性脂肪及挥发性脂肪。<br />
                            &emsp;&emsp;酸性脂肪：即脂肪中含有酸，其强弱会因咖啡种类不同而异。<br />
                            &emsp;&emsp;挥发性脂肪：是咖啡香气主要来源，会散发出约四十种芳香物质。
                        </p>
                        <p>蛋白质：蛋白质在烘培过程中会与糖类参与美拉德反应，进而产生令人沉醉其中的复杂香气。</p>
                        <p>纤维：占咖啡熟豆的70%，不可被萃取出来。</p>
                        <p>矿物质：含有少量石灰、铁质、磷、碳酸钠等。</p>
                        <p></p>
                    </div>
                </el-col>
                <el-col :span="5">
                    <table class="infobox">
                        <caption class="infobox-title">
                            <span>咖啡</span>
                        </caption>
                        <tbody>
                            <tr>
                                <td colspan="2">
                                    <img :src="coffee" class="infobox-imgae" />
                                    <div class="infobox-caption">一杯咖啡</div>
                                </td>
                            </tr>
                            <tr>
                                <th>类型</th>
                                <td>热饮，冷饮</td>
                            </tr>
                            <tr>
                                <th>起源地</th>
                                <td>
                                    也门（最先饮用的国家）<br />
                                    埃塞尔比亚（最早种植的国家）
                                </td>
                            </tr>
                            <tr>
                                <th>创始时间</th>
                                <td>约在15世纪</td>
                            </tr>
                            <tr>
                                <th>颜色</th>
                                <td>深棕色，米色，浅棕色，黑色</td>
                            </tr>
                        </tbody>
                    </table>
                    <br />
                    <table class="infobox">
                        <tbody>
                            <tr>
                                <th>热量</th>
                                <td>2 kJ（0.48 kcal）</td>
                            </tr>
                            <tr>
                                <th>糖类</th>
                                <td>0</td>
                            </tr>
                            <tr>
                                <th class="infobox-label">糖</th>
                                <td>0</td>
                            </tr>
                            <tr>
                                <th class="infobox-label">膳食纤维</th>
                                <td>0</td>
                            </tr>
                            <tr>
                                <th>脂肪</th>
                                <td>0.02</td>
                            </tr>
                            <tr>
                                <th>蛋白质</th>
                                <td>0.12</td>
                            </tr>
                            <tr>
                                <th>维生素</th>
                            </tr>
                            <tr>
                                <th class="infobox-label">硫胺（维生素B1）</th>
                                <td>0.014 mg（1%）</td>
                            </tr>
                            <tr>
                                <th class="infobox-label">核黄素（维生素B2）</th>
                                <td>0.076 mg（6%）</td>
                            </tr>
                            <tr>
                                <th class="infobox-label">烟碱酸（维生素B3）</th>
                                <td>0.191 mg（1%）</td>
                            </tr>
                            <tr>
                                <th class="infobox-label">吡哆醇（维生素B6）</th>
                                <td>0.001 mg（0%）</td>
                            </tr>
                            <tr>
                                <th class="infobox-label">叶酸（维生素B9）</th>
                                <td>2 μg（1%）</td>
                            </tr>
                            <tr>
                                <th class="infobox-label">维生素C</th>
                                <td>0 mg（0%）</td>
                            </tr>
                            <tr>
                                <th class="infobox-label">维生素E</th>
                                <td>0.01 mg（0%）</td>
                            </tr>
                            <tr>
                                <th class="infobox-label">维生素K</th>
                                <td>0.1 μg（0%）</td>
                            </tr>
                            <tr>
                                <th>膳食矿物质</th>
                            </tr>
                            <tr>
                                <th class="infobox-label">钙</th>
                                <td>2 mg（0%）</td>
                            </tr>
                            <tr>
                                <th class="infobox-label">铁</th>
                                <td>0.01 mg（0%）</td>
                            </tr>
                            <tr>
                                <th class="infobox-label">镁</th>
                                <td>3 mg（1%）</td>
                            </tr>
                            <tr>
                                <th class="infobox-label">磷</th>
                                <td>3 mg（0%）</td>
                            </tr>
                            <tr>
                                <th class="infobox-label">钾</th>
                                <td>49 mg（1%）</td>
                            </tr>
                            <tr>
                                <th class="infobox-label">钠</th>
                                <td>2 mg（0%）</td>
                            </tr>
                            <tr>
                                <th class="infobox-label">锌</th>
                                <td>0.02 mg（0%）</td>
                            </tr>
                            <tr>
                                <th>其他成分</th>
                            </tr>
                            <tr>
                                <th class="infobox-label">水</th>
                                <td>99.39 g</td>
                            </tr>
                            <tr>
                                <th class="infobox-label">咖啡因</th>
                                <td>40 mg</td>
                            </tr>
                            <tr>
                                <td
                                    class="infobox-full-data"
                                    clspan="2"
                                    style="font-size: 89%; background: #e0e0e0; padding: 0.15em; line-height: 1.25em"
                                >
                                    <div class="plainlist">
                                        单位<br />
                                        pu = 微克 * mq = 微克<br />
                                        IU = 药学国际单位
                                    </div>
                                </td>
                                <td
                                    class="infobox-full-data"
                                    clspan="2"
                                    style="font-size: 89%; background: #e0e0e0; padding: 0.15em; line-height: 1.25em"
                                >
                                    Link to USDA Database entry<br />
                                    参照美国标准的相对百分比<br />
                                    成人每日的参考膳食摄入量（DRI）
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </el-col>
            </el-row>
        </div>
    </el-main>
</template>

<script setup name="coffee">
import { ref } from "vue";

const coffee = ref("/api/img/wiki/coffee/A_small_cup_of_coffee.JPG");
</script>

<style scoped>
.heading {
    font-family: "Linux Libertine", "Georgia", "Times", "Source Serif 4", serif;
    line-height: 1.375;
}
.content-text {
    margin-top: 16px;
    margin-left: 1rem;
    font-family: sans-serif;
    color: var(--color-base, #202122);
}
.content-text > p {
    line-height: 2rem;
}
.infobox {
    border-spacing: 3px;
    color: inherit;
    font-size: 88%;
    line-height: 1.5em;
    border: 1px solid #a2a9b1;
    border-spacing: 3px;
    background-color: #f8f9fa;
    color: inherit;
    margin: 0.5em 0 0.5em 1em;
    padding: 0.2em;
    /* float: right; */
    clear: right;
    font-size: 88%;
    line-height: 1.5em;
    width: 22em;
}
.infobox-title {
    font-size: 125%;
    font-weight: bold;
    text-align: center;
}
.infobox-imgae {
    border: 0;
    vertical-align: middle;
    word-wrap: break-word;
    width: 250px;
    height: 188px;
    aspect-ratio: auto 250 / 188;
}
.infobox-caption {
    text-align: center;
}
.infobox > tbody > tr > th {
    text-align: left;
    padding-top: 0.245em;
    line-height: 1.15em;
    padding-right: 0.65em;
    white-space: nowrap;
}
.infobox > tbody > tr > td {
    text-align: left;
    vertical-align: top;
}
.infobox-label {
    padding-left: 0.65em;
    line-height: 1.1em;
    font-weight: normal;
    padding-right: 0.25em;
}
.infobox-full-data {
    text-align: center;
    vertical-align: top;
}
.plainlist {
    text-align: left;
    background: #e0e0e0;
    line-height: 1.25em;
    list-style: none;
}

/* 标题背景样式 */
.card-title-primary {
    background-color: #ff69b4;
    margin: 0;
    color: #fff;
    font-size: 20px;
    font-weight: 700;
    padding: 10px 15px;
    border-radius: 10px 10px 0 0;
    background-image: linear-gradient(
        135deg,
        #ffb6c1 25%,
        #ff69b4 25%,
        #ff69b4 50%,
        #ffb6c1 50%,
        #ffb6c1 75%,
        #ff69b4 75%,
        #ff69b4
    );
    background-size: 14px 14px;
}

.card-title-primary .text {
    text-shadow: 2px 2px 5px rgba(0, 0, 0, 0.5);
}
</style>
