import { useCallback, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import * as Api from "../api";
import { PATH } from "../constants/path";
import { RecruitmentItem } from "../../types/domains/recruitments";

const useRecruitmentItem = (recruitmentId: number) => {
  const [recruitmentItems, setRecruitmentItems] = useState<RecruitmentItem[]>([]);
  const navigate = useNavigate();

  const init = useCallback(async () => {
    try {
      const { data } = await Api.fetchRecruitmentItems(recruitmentId);
      setRecruitmentItems(data);
    } catch (error) {
      alert("지원서를 불러오는 데 실패했습니다.");
      navigate(PATH.HOME, { replace: true });
    }
  }, [recruitmentId]);

  useEffect(() => {
    init();
  }, []);

  return { recruitmentItems };
};

export default useRecruitmentItem;