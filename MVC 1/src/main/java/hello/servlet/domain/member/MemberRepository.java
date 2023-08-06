package hello.servlet.domain.member;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시정 문제가 고려되지 있지 않음, 실무에서는 ConcurrnetHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository(); // final 로 인해 instance 객체가 하나가 생김.

    public static MemberRepository getInstance() {
        return instance;
    } // 싱글톤

    private MemberRepository() {
    } // 싱글톤 - 외부에서 MemberRepository 객체를 만드는 것을 방지하는 역할.

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // 이렇게 하는 이유는 store에 있는 값을 건드리고 싶지 않아서 ArrayList를 통해서 조회
    }

    public void clearStore() {
        store.clear();
    }


}
