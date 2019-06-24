from py2neo import Graph, Node, Relationship, NodeMatcher

# 建立数据库连接
test_graph = Graph(
    "http://127.0.0.1:7474",
    username="neo4j",
    password="123456"
)

# 通过cypher语句查询
macth_str = 'match (n:华山) where n.name = "林平之" return n;'

# 解析一：
data = test_graph.run(macth_str).data()
# print(data)  # 输出的属性有中文的话会为unicode编码
# 解析二：通过获取属性输出，可以正常显示
#
# for i in data:
#     print(i.get("n")[0])
#     for x in range(len(i.get("n"))):
#         print(i.get("n")[x])
# print(len(i.get("n")))
# print(type(i.get("n")))


# data = test_graph.run(macth_str).evaluate()
# data_1 = test_graph.run(macth_str).to_table()


# print("%s=>%s" % (i.get("n"), i.get("n")))
# # 解析三：直接转换为pandas的dataframe格式
# df = test_graph.run(macth_str).to_data_frame()
# print(df)

# 可以获取到每一个值
find = NodeMatcher(test_graph).match('华山')
for f in find:
    print(f["name"], f["identity"], f["skill"])
