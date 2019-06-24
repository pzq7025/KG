# coding:utf-8
from py2neo import Graph, Node, Relationship, NodeMatcher


class GraphLInk:
    # 连接neo4j数据库，输入地址、用户名、密码
    def __init__(self):
        self.graph = Graph("http://127.0.0.1:7474", username="neo4j", password="123456")

    def link_db(self):
        try:
            self.__init__()
        except Exception as e:
            print("Error!")
            _ = e.__traceback__

    def get_information(self):
        sql = 'match (n:华山) where n.name = "林平之" return n.name;'
        df = self.graph.run(sql).to_data_frame()
        print(df)

        # 查找全部
        find = NodeMatcher(self.graph).match('华山')
        for f in find:
            print(f["name"], f["identity"], f["skill"])


if __name__ == '__main__':
    graph = GraphLInk()
    graph.get_information()
